package spring.eventlisner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.eventlisner.entity.UserEvent;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent,Long> {
}
