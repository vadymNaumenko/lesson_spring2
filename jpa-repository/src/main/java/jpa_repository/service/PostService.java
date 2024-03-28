package jpa_repository.service;

import jpa_repository.entity.Post;
import jpa_repository.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post findById(Integer id){
        return postRepository.findById(id).orElseThrow();
    }
}
