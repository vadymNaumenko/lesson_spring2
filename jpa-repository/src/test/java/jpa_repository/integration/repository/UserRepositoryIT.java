package jpa_repository.integration.repository;

import jpa_repository.annotation.IT;
import jpa_repository.entity.Role;
import jpa_repository.entity.User;
import jpa_repository.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import java.util.List;

@IT
@RequiredArgsConstructor
public class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void findByFirstnameContainsAndLastnameContains() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
        System.out.println(users);
    }

    @Test
    void findByUsername() {
        List<User> users = userRepository.findAllByUsername("v");
        System.out.println(users);
    }

    @Test
    void updateRole() {
        User ivan = userRepository.findById(1L).get();
        assertSame(Role.ADMIN,ivan.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameIvan = userRepository.findById(1L).get();
        assertSame(Role.USER,theSameIvan.getRole());
    }

}
