package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.EventShortDTO;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void save(List<EventDTO> events, SourceConfig config) {
        List<Event> newEvents = new ArrayList<>(events.size());
        for (EventDTO e : events) {
            Event event = new Event();
            event.setSourceConfig(config);
            event.setTitle(e.getTitle());
            event.setNewsUrl(e.getNewsUrl());
            event.setImageUrl(e.getImageUrl());
            event.setText(e.getText());
            event.setDateTime(e.getDateTime());
            newEvents.add(event);
        }
        eventRepository.saveAll(newEvents);
        log.info("Succesfully saved {} events .", newEvents.size());
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

    public EventDTO findByTitle(String title) {
        return eventRepository.findByTitle(title).map(EventDTO::getInstance).orElse(null);
    }

    public List<EventDTO> filterEvents(List<EventDTO> newsUrl) {
        return newsUrl.stream()
                .filter(event -> {
                    if (eventRepository.existsByNewsUrl(event.getNewsUrl())){
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
