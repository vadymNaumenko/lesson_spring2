package jpa_repository.repository;

import jpa_repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname,String lastname);
    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname,String lastname);

    @Query(value = "SELECT u.* FROM users WHERE u.username = :username",nativeQuery = true)
    List<User> findAllByUsername(String username);


}
