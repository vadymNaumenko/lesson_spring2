package news.crawler.domin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceConfig {
    private Integer id;
    private String sourceUrl;
    private String selectTag;
    ////////////////////////////////
    private String tagForHref;
    private String tagForTitle;
    private String nextPage;

}
