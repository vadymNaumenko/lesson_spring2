package spring.eventlisner.listner;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.eventlisner.Repository.UserEventRepository;
import spring.eventlisner.entity.User;
import spring.eventlisner.entity.UserEvent;

@Component
@RequiredArgsConstructor
public class EntityListener {

    private final UserEventRepository userEventRepository;

//    @EventListener
//    public void createEntity(EventEntity<User> event) {
//        String name = "";
//        if (event.getSource() instanceof User) {
//            name = ((User) event.getSource()).getName();
//        }
//
//        userEventRepository.save(UserEvent.builder()
//                .Name(name)
//                .type(AccessType.CREATED)
//                .build());
//        System.out.println("created entity " + event);
//    }
//    @EventListener(condition = "#root.args[0].getAccessType().name() == 'CREATE'")
    @EventListener
    public void createEntity(EventEntity<User> event) {
        String name = "";
        if (event.getSource() instanceof User) {
            name = ((User) event.getSource()).getName();
        }

        userEventRepository.save(UserEvent.builder()
                .Name(name)
                .type(AccessType.CREATED)
                .build());
        System.out.println("created entity " + event);
    }
}
