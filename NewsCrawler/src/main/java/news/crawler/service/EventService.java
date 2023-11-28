package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.EventShortDTO;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;
import news.crawler.mapper.MapperToEvent;
import news.crawler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<EventShortDTO> findAllByTitle(String title){

        return eventRepository
                .findAllBy(title).stream()
                .map(EventShortDTO::getInstance)
                .collect(Collectors.toList());
    }

    public List<EventShortDTO> findByPageable(Integer number){
        Sort.TypedSort<Event> sort = Sort.sort(Event.class);
        sort.by(Event::getDateTime);

        PageRequest pageRequest = PageRequest.of(number,3, sort.descending());

        return eventRepository.findAllBy(pageRequest).stream()
                .map(EventShortDTO::getInstance)
                .collect(Collectors.toList());
    }

}
