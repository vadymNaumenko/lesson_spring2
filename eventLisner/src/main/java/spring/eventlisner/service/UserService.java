package spring.eventlisner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import spring.eventlisner.Repository.EventRepository;
import spring.eventlisner.Repository.UserRepository;
import spring.eventlisner.entity.AccessType;
import spring.eventlisner.entity.EventEntity;
import spring.eventlisner.entity.User;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ApplicationEventPublisher event;
    private static int item = 0;

    public User create(String name) {

        User newUser = userRepository.save(User.builder()
                .userId(item++)
                .name(name)
                .build());

        event.publishEvent(new EventEntity<User>(newUser, AccessType.CREATED));
        return newUser;
    }

    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            event.publishEvent(new EventEntity<User>(user.get(), AccessType.READ));
            return user.get();
        }
        return null;
    }

    public User update(Integer id, User updateUser) {
        Optional<User> byId = userRepository.findById(id);
        return byId.map(user -> {
            user.setName(updateUser.getName());
            User newUser = userRepository.save(user);
            event.publishEvent(new EventEntity<>(newUser, AccessType.UPDATE));
            return newUser;
        }).orElse(null);
    }

}
