package com.example.websocket.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotificationDTO {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private String content;

}
