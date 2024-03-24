package spring.eventlisner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.eventlisner.entity.EventEntity;
import spring.eventlisner.entity.User;

@Repository
public interface EventRepository extends JpaRepository<EventEntity<User>,Long> {
}
