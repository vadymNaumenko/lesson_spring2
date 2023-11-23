package news.crawler.repository;

import news.crawler.domin.SourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceConfigRepository extends JpaRepository<SourceConfig,Integer> {
}
