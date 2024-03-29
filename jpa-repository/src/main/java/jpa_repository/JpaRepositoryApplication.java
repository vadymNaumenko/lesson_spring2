package jpa_repository;

import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaRepositoryApplication.class, args);
    }

}
