package app.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;


    @Column(name = "age")
    private int age;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "created_who")
    @NotEmpty
    private String createdWho;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {
    }

//    protected boolean canEqual(final Object other) {
//        return other instanceof Person;
//    }

}
