package spring.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String username;
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    @Column(nullable = false)
    private String password;

    public Person(String username, Integer yearOfBirth) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

//    protected boolean canEqual(final Object other) {
//        return other instanceof Person;
//    }

}
