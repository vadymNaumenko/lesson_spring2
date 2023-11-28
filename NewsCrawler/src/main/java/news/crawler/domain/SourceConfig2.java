package news.crawler.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceConfig2 {
    private Integer id;
    private String sourceUrl;
    private String selectTag;
    ////////////////////////////////

//    private Integer id;
//    private String rootUrl;
//    private String newsSuffix;
//    private String selectTag;


    private String pathToTitle;
    private int counterForTitle;
    private String pathToTxt;
    private String pathToImg;
    private String pathToHrefForPage;


}
