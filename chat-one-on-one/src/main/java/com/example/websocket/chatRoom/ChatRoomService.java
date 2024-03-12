package com.example.websocket.chatRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomId(
            String senderNickName,
            String recipientIdNickName,
            boolean createNewRoomIfNotExists
    ) {
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderNickName, recipientIdNickName)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                        var chatId = createChatId(senderNickName, recipientIdNickName);
                        return Optional.of(chatId);
                    }

                    return  Optional.empty();
                });
    }
//    public Optional<String> getChatRoomId(
//            String senderId,
//            String recipientId,
//            boolean createNewRoomIfNotExists
//    ) {
//        return chatRoomRepository
//                .findBySenderIdAndRecipientId(senderId, recipientId)
//                .map(ChatRoom::getChatId)
//                .or(() -> {
//                    if(createNewRoomIfNotExists) {
//                        var chatId = createChatId(senderId, recipientId);
//                        return Optional.of(chatId);
//                    }
//
//                    return  Optional.empty();
//                });
//    }

    private String createChatId(String senderNickName, String recipientNickName) {
        var chatId = String.format("%s_%s", senderNickName, recipientNickName);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderNickName)
                .recipientId(recipientNickName)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(recipientNickName)
                .recipientId(senderNickName)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }
}
