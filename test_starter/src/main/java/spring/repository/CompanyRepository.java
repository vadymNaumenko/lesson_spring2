package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
