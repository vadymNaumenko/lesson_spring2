package news.crawler.controller.dto;

import lombok.*;
import news.crawler.domain.SourceConfig;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SourceConfigDTO {

    private Integer id;
    private String rootUrl;
    private String newsSuffix;
    private String ClassName;
    private Boolean disabled;

    public static SourceConfigDTO getInstance(SourceConfig sourceConfig) {
        return new SourceConfigDTO(sourceConfig.getId(),
                sourceConfig.getRootUrl(), sourceConfig.getNewsSuffix(),
                sourceConfig.getClassName(), sourceConfig.getDisabled());
    }

    public SourceConfigDTO(String rootUrl, String newsSuffix, String className, Boolean disabled) {
        this.rootUrl = rootUrl;
        this.newsSuffix = newsSuffix;
        ClassName = className;
        this.disabled = disabled;
    }

}
