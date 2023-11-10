package spring.controller.dto;

import lombok.*;
import spring.domin.Person;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String username;
    private String city;


    public static PersonDTO getInstance(Person person){
        return new PersonDTO(person.getId(),person.getUsername(), person.getCity());
    }

}
