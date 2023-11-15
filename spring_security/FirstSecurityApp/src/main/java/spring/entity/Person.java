package spring.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 3, max = 30, message = "username mast be from 2 to 30 simbl")
    @Column( nullable = false)
    private String username;
    @Min(value = 1900, message = "year mast be more 1900")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    @Column(nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

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
