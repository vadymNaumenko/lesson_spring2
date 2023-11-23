package news.crawler.service.executer;

import news.crawler.controller.dto.EventDTO;
import news.crawler.domin.SourceConfig;

import java.util.List;

public interface Execute {
    List<EventDTO> execute(SourceConfig sourceConfig);
}
