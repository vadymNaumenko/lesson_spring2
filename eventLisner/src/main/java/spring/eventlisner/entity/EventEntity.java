package spring.eventlisner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Entity
public class EventEntity<T> extends ApplicationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AccessType accessType;
    public EventEntity(T source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
    public EventEntity() {
        super(null);
    }
}
