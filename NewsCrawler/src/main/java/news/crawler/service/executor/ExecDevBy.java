package news.crawler.service.executor;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;

import java.util.List;

@Slf4j
public class ExecDevBy implements Execute{

    @Override
    public List<EventDTO> execute(SourceConfig sourceConfig) {
      log.info("ExecDevBy...");
      return null;
    }

    @Override
    public List<EventDTO> readNews(List<EventDTO> eventDTOS) {
        return null;
    }

    @Override
    public List<EventDTO> readUrl(SourceConfig sourceConfig) {
        return null;
    }

}
