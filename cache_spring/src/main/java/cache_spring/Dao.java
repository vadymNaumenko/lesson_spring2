package cache_spring;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class Dao {

    @SneakyThrows
    public String getData() {
        Thread.sleep(2000);
        List<String> str = Files.readAllLines(Path.of("src/main/resources/ivanov.txt"));
        return String.join(" ", str);
    }

}
