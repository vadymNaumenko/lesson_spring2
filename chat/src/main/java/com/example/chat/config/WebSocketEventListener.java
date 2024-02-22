package com.example.chat.config;

import com.example.chat.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate; // Шаблон отправки сообщений

    // Обработчик события отключения WebSocket соединения
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage()); // Обертка заголовков сообщения
        String username = (String) headerAccessor.getSessionAttributes().get("username"); // Получение имени пользователя из атрибутов сессии
        if (username != null) { // Проверка, что имя пользователя не равно null
            log.info("User disconnected: {}", username); // Вывод информации о пользователе, который отключился
            var chatMessage = ChatMessage.builder() // Создание объекта сообщения чата
                    .type(ChatMessage.MessageType.LEAVE)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage); // Отправка сообщения о выходе пользователя в публичный канал
        }
    }
}
