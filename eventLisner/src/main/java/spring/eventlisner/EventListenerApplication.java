package spring.eventlisner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.eventlisner.configuration.ConfigBean;
import spring.eventlisner.entity.EventEntity;
import spring.eventlisner.service.UserService;

@SpringBootApplication
public class EventListenerApplication {

    private static UserService userService;
    public static void main(String[] args) {

        SpringApplication.run(EventListenerApplication.class, args);

        var context = new AnnotationConfigApplicationContext();
        context.register(ConfigBean.class);
        context.refresh();

        EventEntity event = context.getBean("eventEntity",EventEntity.class);
        System.out.println(event);

        System.out.println(userService.findById(7));
        System.out.println(userService.create("roma"));

    }

}
