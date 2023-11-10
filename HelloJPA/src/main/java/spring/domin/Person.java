package spring.domin;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "event")
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    private String city;


    public Person(String username, String city) {
        this.username = username;
        this.city = city;
    }
}
