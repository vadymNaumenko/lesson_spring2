package jpa_repository.integration.repository;

import jpa_repository.annotation.IT;
import jpa_repository.entity.User;
import jpa_repository.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@IT
@RequiredArgsConstructor
public class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void findByFirstnameContainsAndLastnameContains(){
        List<User> users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);
        System.out.println(users);
    }
    @Test
    void findByUsername(){
        List<User> users = userRepository.findAllByUsername("v");
        System.out.println(users);
    }

}
