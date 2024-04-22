package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.User;
import spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User with id: "+id+" not found"));
    }
}
