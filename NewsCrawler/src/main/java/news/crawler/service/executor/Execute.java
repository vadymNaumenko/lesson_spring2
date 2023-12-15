package news.crawler.service.executor;

import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;

import java.util.List;

public interface Execute {
//   default List<EventDTO> execute(SourceConfig sourceConfig){
//       return null;
//   }
    List<EventDTO> readNews(List<EventDTO> eventDTOS);
    List<EventDTO> readUrl(SourceConfig sourceConfig);
}
