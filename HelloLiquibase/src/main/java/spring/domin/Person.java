package spring.domin;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Person(String username, City city) {
        this.username = username;
        this.city = city;
    }
}
