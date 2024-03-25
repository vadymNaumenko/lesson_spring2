package spring.eventlisner.listner;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.eventlisner.entity.EventEntity;
import spring.eventlisner.entity.User;

@Component
public class EntityListener {

    @EventListener
    public void acceptEntity(EventEntity<User> event){
        System.out.println("entity " + event);
    }
}
