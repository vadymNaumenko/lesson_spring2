package jpa_repository.repository;

import jpa_repository.entity.Role;
import jpa_repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname,String lastname);
    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    //    @Query(value = "SELECT u.* FROM users WHERE u.username = :username",nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u" +
           " set u.role = :role" +
           " where u.id in (:ids)")
    int updateRole(Role role, Long... ids);


    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);

    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    //can return
    // java - Collection, Stream
    //spring - Streamable, Slice, Page
    //todo List<User> findAllBy(Pageable pageable);
    //todo    Slice<User> findAllBy(Pageable pageable);
    @Query(value = "select u from User u",
    countQuery = "select count(distinct u.firstname) from User u") //переопредиления  каунтера
    Page<User> findAllBy(Pageable pageable);
}
