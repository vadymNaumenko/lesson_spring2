package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domin.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
