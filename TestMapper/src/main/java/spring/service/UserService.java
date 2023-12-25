package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.convert.UserDTOConverter;
import spring.dto.UserDTO;
import spring.model.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDTOConverter userDTOConverter;
    public UserDTO getUser(){

         User user = new User();
         user.setId(123);
         user.setFirstname("Robin");
         user.setLastname("Jons");
         user.setEmail("jons@gmail.com");
         user.setPassword("password");

         UserDTO userDTO = userDTOConverter.convertUserToUserDTO(user);



        return userDTO;
    }
}
