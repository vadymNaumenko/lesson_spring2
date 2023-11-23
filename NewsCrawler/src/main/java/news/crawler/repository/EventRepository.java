package news.crawler.repository;

import news.crawler.domin.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    boolean findByTitleIs(String title);
}
    //    boolean isByNewsUrl(String url);


//    List<Event> findTop3();