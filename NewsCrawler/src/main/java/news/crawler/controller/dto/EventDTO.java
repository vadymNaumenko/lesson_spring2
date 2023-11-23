package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import news.crawler.domin.SourceConfig;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {

    private SourceConfig sourceConfig;

    private String title;
    private String newsUrl;
    //    private OffsetDateTime dateTime;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String text;


}