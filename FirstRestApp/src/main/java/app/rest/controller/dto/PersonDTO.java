package app.rest.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    @NotEmpty(message = "Name should not empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 characters")
    private String name;

    private int age;
    @Email
    private String email;

}
