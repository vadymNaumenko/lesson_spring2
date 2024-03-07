package spring_and_kafka.entity;

import lombok.*;
import spring_and_kafka.entity.type.MessageType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private MessageType type;
    private String content;
    private String sender;
    private String sessionId;

}
