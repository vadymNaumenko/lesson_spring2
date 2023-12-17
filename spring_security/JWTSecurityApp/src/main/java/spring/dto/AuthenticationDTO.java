package spring.dto;

import lombok.Data;

import javax.validation.constraints.Size;
@Data
public class AuthenticationDTO {
    @Size(min = 3, max = 30, message = "username mast be from 2 to 30 simbl")
    private String username;
    private String password;
}
