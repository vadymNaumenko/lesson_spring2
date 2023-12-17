package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.domin.City;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query("select c from City c where c.name like :name")
    City findByName(String name);
}
