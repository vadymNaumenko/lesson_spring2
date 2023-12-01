package news.crawler.service.executor;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.SourceConfigRepository;
import news.crawler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.List;

@Slf4j
@Service
public class CrawlerExecutor implements SmartLifecycle {


    @Value("${executor.time:60}")
    private int time;

    @Autowired
    private SourceConfigRepository sourceConfigRepository;
    @Autowired
    private EventService eventService;
    private final String PACKAGE = "news.crawler.service.executor.";

    private enum ThreadStatus {
        RUNNING, STOP_REQUEST, STOPPED
    }

    private ThreadStatus status = ThreadStatus.STOPPED;
    private final Object lock = new Object();

    public void run() {
        synchronized (lock) {

            while (status == ThreadStatus.RUNNING) {
                List<SourceConfig> configs = sourceConfigRepository.findAll();
                for (SourceConfig config : configs) {
                    if (config.getDisabled() == null || !config.getDisabled()) {
                        try {
                            Class<?> cls = Class.forName(PACKAGE+config.getClassName());
                            Constructor<?> constructor = cls.getConstructor();
                            Execute execClass = (Execute) constructor.newInstance();
                            List<EventDTO> events = execClass.execute(config);
                            log.info("Read {} events from {}", events.size(), config.getRootUrl());
                            //save to database
                            eventService.save(events, config);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {

                    lock.wait(1000 * 60 * time);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    break;
                }
            }
            status = ThreadStatus.STOPPED;
            lock.notifyAll();

        }
    }

    @Override
    public void start() {
        log.info("Service starting...");
        status = ThreadStatus.RUNNING;

        new Thread(() -> {
            run();
        }).start();

        log.info("Service start");


    }

    @Override
    public void stop() {
        log.info("Service stopping");
        status = ThreadStatus.STOPPED;
        synchronized (lock) {
            lock.notifyAll();
            while (status != ThreadStatus.STOPPED) {
                try {
                    lock.wait(1000 * 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //TODO stop service
        log.info("Service stopped.");


    }

    @Override
    public boolean isRunning() {

        return status == ThreadStatus.RUNNING;
    }
}
