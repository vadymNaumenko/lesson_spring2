package jpa_repository.integration.repository;

import jpa_repository.annotation.IT;
import jpa_repository.dto.PersonalInfo;
import jpa_repository.dto.PersonalInfo2;
import jpa_repository.entity.Role;
import jpa_repository.entity.User;
import jpa_repository.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@IT
@RequiredArgsConstructor
public class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void checkProjections(){
//        List<PersonalInfo> users = userRepository.findAllByCompanyId(1, PersonalInfo.class);
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable(){
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Slice<User> userSlice = userRepository.findAllBy(pageable);
        assertThat(userSlice).hasSize(2);

        userSlice.forEach(user -> System.out.println(user.getUsername()));

        while (userSlice.hasNext()){
            userSlice = userRepository.findAllBy(userSlice.nextPageable());
            userSlice.forEach(user -> System.out.println(user.getUsername()));

        }
    }
    @Test
    void checkSort() {
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sort = sortBy.by(User::getFirstname)
                .and(sortBy.by(User::getLastname));

        List<User> top3 = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort.descending());
        assertThat(top3).hasSize(3);
    }

    @Test
    void findTop3WithSort() {
        Sort sortById = Sort.by("firstname").and(Sort.by("lastname"));
        List<User> top3 = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortById.descending());
        assertThat(top3).hasSize(3);
    }

    @Test
    void findTop3ByBerthDateBeforeDesc() {
        List<User> top3 = userRepository.findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate.now());
        assertThat(top3).hasSize(3);
    }

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
        assertSame(Role.ADMIN, ivan.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameIvan = userRepository.findById(1L).get();
        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    void findToByIdDesc() {
        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5, user.getId()));
    }

}
