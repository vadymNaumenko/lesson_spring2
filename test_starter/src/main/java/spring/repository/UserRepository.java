package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import spring.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String username);
}
