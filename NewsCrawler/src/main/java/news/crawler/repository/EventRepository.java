package news.crawler.repository;

import news.crawler.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    boolean existsByNewsUrl(String title);
    Page<Event> findAllBy(Pageable pageable);
    @Query("select e from Event e where e.title like %:title%")
    List<Event> findAllBy(String title);

    Optional<Event> findByTitle(String title);

}