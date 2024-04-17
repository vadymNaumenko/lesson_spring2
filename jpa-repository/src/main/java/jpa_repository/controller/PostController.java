package jpa_repository.controller;

import jpa_repository.repository.PostRepository;
import jpa_repository.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository repository;

    @GetMapping
    public void get(){
        System.out.println();
    }
}
