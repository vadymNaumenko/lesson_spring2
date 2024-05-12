package jpa_repository.repository;

import jpa_repository.entity.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    @Query(name = "company.findByName")
//    Optional<Company> findByName(@Param("name") String name); NamedQuery

//    @Query("select c from Company c join fetch c.locales cl where c.name = :name")
    @Query("select c from Company c where c.name = :name")
    Optional<Company> findByName(String name);
    List<Company> findByNameContainingIgnoreCase(String name);

//    List<Company> findAllByTitleContainingAndDescriptionContaining(String title, String description);

//    @Query("select p from Company p" +
//           " where p.name like %:name% and p.description like %:description%")
//    List<Company> findAllBy(String name, String description);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Company p " +
           "set p.name = :name " +
           "where p.id in (:ids)")
    int updateLocation(String name, Integer... ids);

    @Query("select p from Company p" +
           " join fetch p.locales pl where p.name = :name")
    Optional<Company> findByTitle2(String name);

   Optional<Company> findTopByOrderByIdDesc();
   List<Company> findTop3ByNameBeforeOrderByIdDesc(String title);
   List<Company> findTop2ByNameBefore(String title, Sort sort);
//   List<Post> findTop3(Pageable pageable);

}
