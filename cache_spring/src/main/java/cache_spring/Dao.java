package cache_spring;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class Dao {

    @SneakyThrows
    public String getData(){
        Thread.sleep(2000);
        return "даные";
    }

}
