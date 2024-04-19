package jpa_repository.service;

import jpa_repository.entity.Post;
import jpa_repository.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post findById(Integer id){
        return postRepository.findById(id).orElseThrow();
    }

    public void findTopAndSort(){
        Sort.TypedSort<Post> typedSort = Sort.sort(Post.class);
        Sort sortAnd = typedSort.by(Post::getId).and(typedSort.by(Post::getTitle));
        Sort sortById = Sort.by("id");
        postRepository.findTop3ByTitleBefore("lol",sortById.descending());
    }
    public void findByPageable(){
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("id"));
        postRepository.findTop3(pageRequest);
    }
}
