package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;

import java.time.LocalDateTime;

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


    public static EventDTO getInstance(Event even){
        return new EventDTO(even.getSourceConfig(), even.getTitle(), even.getNewsUrl(),
                even.getDateTime(), even.getImageUrl(), even.getText());
    }


}