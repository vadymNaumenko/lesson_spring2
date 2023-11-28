package news.crawler.mapper;

import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.Event;
import org.springframework.stereotype.Component;

@Component
public  class MapperToEvent {

    public static Event mapToEvent(EventDTO eventDTO) {
        return new Event(eventDTO.getSourceConfig(),eventDTO.getTitle(),
                eventDTO.getNewsUrl(), eventDTO.getDateTime(),
                eventDTO.getImageUrl(), eventDTO.getText());
    }
}
