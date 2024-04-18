package jpa_repository.repository;

import jpa_repository.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where p.title = :title")
    Optional<Post> findByTitle(String title);

    List<Post> findAllByTitleContainingAndDescriptionContaining(String title, String description);

    @Query("select p from Post p" +
           " where p.title like %:title% and p.description like %:description%")
    List<Post> findAllBy(String title, String description);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Post p " +
           "set p.title = :title " +
           "where p.id in (:ids)")
    int updateLocation(String title, Integer... ids);

    @Query("select p from Post p" +
           " join fetch p.locales pl where p.title = :title")
    Optional<Post> findByTitle2(String title);
}
