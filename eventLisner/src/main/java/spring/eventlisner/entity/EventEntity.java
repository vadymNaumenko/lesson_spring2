package spring.eventlisner.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class EventEntity extends ApplicationEvent {
    private final AccessType accessType;
    private Integer id;

    public EventEntity(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
