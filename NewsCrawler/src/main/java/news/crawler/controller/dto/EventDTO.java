package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    private String title;
    private String url;
    private List<String> fullText;

    public EventDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
