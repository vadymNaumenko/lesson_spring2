package news.crawler.repository;

import news.crawler.domain.SourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceConfigRepository extends JpaRepository<SourceConfig,Integer> {
    @Query("select e from Event e")
    Long countAll();
}
