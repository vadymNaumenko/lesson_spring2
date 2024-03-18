package spring.eventlisner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.eventlisner.Repository.UserRepository;
import spring.eventlisner.entity.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static int item = 0;

    public User create(String name){
       return userRepository.save(User.builder()
                .userId(item++)
                .name(name)
                .build());
    };
    public User findById(int id){
       return userRepository.findById(id);
    }
}
