package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domin.SourceConfig;
import news.crawler.mapper.MapperToEvent;
import news.crawler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void save(List<EventDTO> events, SourceConfig config){
        List<EventDTO> eventDTOS = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {
//            if (!eventRepository.findByTitleIs(events.get(i).getTitle())){
                eventDTOS.add(events.get(i));
//            }
            System.out.println("ss");
        }

        eventRepository.saveAll(eventDTOS.stream()
                .map(MapperToEvent::mapToEvent)
                .map(event -> {event.setSourceConfig(config);
                    return event;}).collect(Collectors.toList())) ;
        log.info("{} events save succesfull",events);

    }

}
