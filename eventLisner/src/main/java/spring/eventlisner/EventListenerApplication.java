package spring.eventlisner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.eventlisner.configuration.ConfigBean;
import spring.eventlisner.entity.EventEntity;

@SpringBootApplication
public class EventListenerApplication {

    public static void main(String[] args) {

        SpringApplication.run(EventListenerApplication.class, args);

//        var context = new AnnotationConfigApplicationContext();
//        context.register(ConfigBean.class);
//        context.refresh();
//
//        EventEntity event = context.getBean("eventEntity",EventEntity.class);
        System.out.println(event);


    }

}
