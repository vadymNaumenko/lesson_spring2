package jpa_repository;

import jpa_repository.service.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class JpaRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaRepositoryApplication.class, args);
    }

}
