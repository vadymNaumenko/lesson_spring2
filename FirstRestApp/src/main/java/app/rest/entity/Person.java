package app.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(nullable = false)
    private String email;


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

//    protected boolean canEqual(final Object other) {
//        return other instanceof Person;
//    }

}
