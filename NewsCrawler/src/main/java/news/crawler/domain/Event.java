package news.crawler.domain;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_config_id")
    private SourceConfig sourceConfig;

    private String title;
    private String newsUrl;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String text;

    public Event(SourceConfig sourceConfig, String title, String newsUrl, LocalDateTime dateTime, String imageUrl, String text) {
        this.sourceConfig = sourceConfig;
        this.title = title;
        this.newsUrl = newsUrl;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.text = text;
    }
}