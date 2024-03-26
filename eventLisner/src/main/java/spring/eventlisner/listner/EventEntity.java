package spring.eventlisner.listner;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
//@Entity
public class EventEntity<T> extends ApplicationEvent {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private AccessType accessType;
    public EventEntity(T source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
    public EventEntity() {
        super("");
    }
}
