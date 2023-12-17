package newsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {

    // 2:00;00
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
