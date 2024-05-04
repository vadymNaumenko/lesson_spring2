package jpa_repository.integration.service;

import org.junit.jupiter.api.Test;
import jpa_repository.annotation.IT;
import jpa_repository.entity.Post;
import jpa_repository.service.PostService;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@IT
@RequiredArgsConstructor
public class PostServiceIT {

    private final PostService postService;

    @Test
    public void findById() {
        Post actual = postService.findById(1);
        assertTrue(actual != null);
        assertEquals(1, actual.getId());
    }

    @Test
    public void findTopAndSort() {
        Sort.TypedSort<Post> typedSort = Sort.sort(Post.class);
        Sort sortAnd = typedSort.by(Post::getId).and(typedSort.by(Post::getTitle));

        List<Post> posts = postService.findTop3ByTitleBefore("путин",sortAnd.descending());
        assertFalse(posts.isEmpty());

    }

    @Test
    public void findByPageable() {
//        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("id"));
//        postRepository.findTop3(pageRequest);
    }
}
