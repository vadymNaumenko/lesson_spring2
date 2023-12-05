package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.EventShortDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;
import news.crawler.mapper.MapperToEvent;
import news.crawler.repository.EventRepository;
import news.crawler.service.executor.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void save(List<EventDTO> events, SourceConfig config) {

        int size = eventRepository.saveAll(events.stream()
                .filter(e -> !eventRepository.existsByNewsUrl(e.getNewsUrl()))
                .map(MapperToEvent::mapToEvent)
                .map(event -> {
                    event.setSourceConfig(config);
                    return event;
                }).collect(Collectors.toList())).size();

        log.info("{} events save succesfull", size);
    }

    public List<EventShortDTO> findAll() {
        return eventRepository
                .findAll(Sort.by("dateTime").descending())
                .stream().map(EventShortDTO::getInstance)
                .collect(Collectors.toList());
    }

    public List<EventShortDTO> findAllByTitle(String title) {

        return eventRepository
                .findAllBy(title).stream()
                .map(EventShortDTO::getInstance)
                .collect(Collectors.toList());
    }

    public List<EventShortDTO> findByPageable(Integer number) {
        Sort.TypedSort<Event> sort = Sort.sort(Event.class);
        sort.by(Event::getDateTime);

        PageRequest pageRequest = PageRequest.of(number, 7, sort.descending());

        return eventRepository.findAllBy(pageRequest).stream()
                .map(EventShortDTO::getInstance)
                .collect(Collectors.toList());
    }

    public Page<EventShortDTO> findByPage(Pageable pageable) {
        Sort.TypedSort<Event> sort = Sort.sort(Event.class);
        sort.by(Event::getDateTime);

        PageRequest pageRequest;
        if (pageable == null) {
            pageRequest = PageRequest.of(0, 7, sort.descending());
            return eventRepository.findAllBy(pageRequest).map(EventShortDTO::getInstance);
        } else {

            return eventRepository.findAllBy(pageable).map(EventShortDTO::getInstance);
        }

    }

    public List<EventDTO> parseTest(String rootUrl, String newsSuffix, String className) {


        Class<?> cls = null;
        try {

            cls = Class.forName("news.crawler.service.executor." + className);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Constructor<?> constructor = null;
        try {

            constructor = cls.getConstructor();

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        Execute execClass = null;
        try {

            execClass = (Execute) constructor.newInstance();

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return execClass.execute(new SourceConfig("http://" + rootUrl, "/" + newsSuffix + "/"));

    }

    public List<EventDTO> parseTest(SourceConfigDTO config) {
        return parseTest(config.getRootUrl(), config.getNewsSuffix(), config.getClassName());
    }

    public EventDTO findByTitle(String title) {
        return eventRepository.findByTitle(title).map(EventDTO::getInstance).orElse(null);
    }
}
