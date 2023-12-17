package spring.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class PersonDTO {

    @Size(min = 3, max = 30, message = "username mast be from 2 to 30 simbl")
    @Column( nullable = false)
    private String username;
    @Min(value = 1900, message = "year mast be more 1900")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    @Column(nullable = false)
    private String password;

}
