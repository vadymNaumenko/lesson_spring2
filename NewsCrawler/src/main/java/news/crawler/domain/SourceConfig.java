package news.crawler.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SourceConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_config_id")
    private Integer id;

    private String rootUrl;
    private String newsSuffix;
    private String ClassName;
    private Boolean disabled;


    public SourceConfig(String rootUrl, String newsSuffix, String className, Boolean disabled) {
        this.rootUrl = rootUrl;
        this.newsSuffix = newsSuffix;
        ClassName = className;
        this.disabled = disabled;
    }

    public SourceConfig(String rootUrl, String newsSuffix) {
        this.rootUrl = rootUrl;
        this.newsSuffix = newsSuffix;

    }
}
