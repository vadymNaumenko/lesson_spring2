package spring.eventlisner.listner;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.eventlisner.event.EventEntity;

@Component
public class EntityListener {

    @EventListener
    public void acceptEntity(EventEntity event){
        System.out.println("entity " + event);
    }
}
