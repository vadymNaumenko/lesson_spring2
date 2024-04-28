package spring.integration.service;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import spring.annotation.IT;

@IT
public abstract class IntegrationTestBase {
    private static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.4.0");
    @BeforeAll
    static void runContainer(){
        container.start();
    }
    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
