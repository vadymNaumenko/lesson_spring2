package spring.annotation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public @interface IT {
    //context caching https://github.com/dmdev2020/spring-starter/tree/lesson-41 45
}
