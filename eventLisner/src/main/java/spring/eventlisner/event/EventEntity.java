package spring.eventlisner.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class EventEntity extends ApplicationEvent {
    private final AccessType accessType;

    public EventEntity(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
