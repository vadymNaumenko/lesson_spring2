package jpa_repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString(exclude = "locales")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Locales locales;
}
