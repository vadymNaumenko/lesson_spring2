package jpa_repository.repository;

import jpa_repository.entity.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("select p from Company p where p.title = :title")
    Optional<Company> findByTitle(String title);

    List<Company> findAllByTitleContainingAndDescriptionContaining(String title, String description);

    @Query("select p from Company p" +
           " where p.title like %:title% and p.description like %:description%")
    List<Company> findAllBy(String title, String description);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Company p " +
           "set p.title = :title " +
           "where p.id in (:ids)")
    int updateLocation(String title, Integer... ids);

    @Query("select p from Company p" +
           " join fetch p.locales pl where p.title = :title")
    Optional<Company> findByTitle2(String title);

   Optional<Company> findTopByOrderByIdDesc();
   List<Company> findTop3ByTitleBeforeOrderByIdDesc(String title);
   List<Company> findTop2ByTitleBefore(String title, Sort sort);
//   List<Post> findTop3(Pageable pageable);

}
