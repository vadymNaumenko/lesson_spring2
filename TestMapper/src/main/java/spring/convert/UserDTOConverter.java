package spring.convert;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import spring.dto.UserDTO;
import spring.model.User;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {

    private final ModelMapper modelMapper;
    public UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        userDTO.setFullName(user.getFirstname()+ " " + user.getLastname());
        return userDTO;
    }
    public User convertUserDtoToUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        return user;
    }
}
