package jpa_repository.repository;

import jpa_repository.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.title = : title")
    public Optional<Post> findByTitle(String title);

    public Optional<Post> findAllByTitleContainingAndDescriptionContaining(String title, String description);
    @Query("select p from Post p" +
           " where p.title like %:title% and p.description like %:description%")
    public Optional<Post> findAllBy(String title, String description);

    @Query("select p from Post p" +
           " join fetch p.locales pl where p.title = : title")
    public Optional<Post> findByTitle2(String title);
}
