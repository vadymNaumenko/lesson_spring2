package spring.eventlisner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import spring.eventlisner.Repository.UserRepository;
import spring.eventlisner.entity.AccessType;
import spring.eventlisner.entity.EventEntity;
import spring.eventlisner.entity.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher event;
    private static int item = 0;

    public User create(String name){

       User newUser = userRepository.save(User.builder()
                .userId(item++)
                .name(name)
                .build());
       if (newUser != null){
           event.publishEvent(new EventEntity(newUser, AccessType.CREATED));
           return newUser;
       }
       return null;
    }

    public User findById(int id){
       User user = userRepository.findById(id);
       if (user != null){
           event.publishEvent(new EventEntity(user,AccessType.READ));
       return user;
       }
       return null;
    }

}
