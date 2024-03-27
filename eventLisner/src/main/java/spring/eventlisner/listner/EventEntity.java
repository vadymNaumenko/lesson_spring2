package spring.eventlisner.listner;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class EventEntity<T> extends ApplicationEvent {

    private AccessType accessType;
    public EventEntity(T source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
