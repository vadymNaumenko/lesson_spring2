package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
