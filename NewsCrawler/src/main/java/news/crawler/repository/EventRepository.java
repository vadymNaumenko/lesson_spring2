package news.crawler.repository;

import news.crawler.domin.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    boolean existsByTitle(String title);
}