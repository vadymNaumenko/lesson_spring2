package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
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

    public SourceConfig getSourceConfig() {
        return sourceConfig;
    }

    public void setSourceConfig(SourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}