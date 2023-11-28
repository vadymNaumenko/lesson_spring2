package news.crawler.mapper;

import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.SourceConfig;

public class MapToSourceConfig {
    public static SourceConfig mapToSource(SourceConfigDTO config) {
        return new SourceConfig(config.getRootUrl(), config.getNewsSuffix(), config.getClassName(), config.getDisabled());
    }
}
