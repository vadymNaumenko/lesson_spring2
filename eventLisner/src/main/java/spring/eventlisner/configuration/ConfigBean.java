package spring.eventlisner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.eventlisner.entity.User;

@Configuration
public class ConfigBean {
    @Bean
    public User vadim() {
        User user = new User();
        user.setName("vadim");
        user.setUserId(1);
        return new User();
    }
    @Bean
    public User dima() {
        User user = new User();
        user.setName("dima");
        user.setUserId(2);
        return new User();
    }

}
