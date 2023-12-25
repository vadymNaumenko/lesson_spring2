package spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String fullName;
}
