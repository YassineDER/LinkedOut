package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ichat.backend.model.tables.messaging.ChatMessage;
import org.ichat.backend.services.social.IChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final IChatService chatService;

    @MessageMapping("/chat/send/{conversationId}")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor,
            @DestinationVariable("conversationId") Long conversationId) {
        chatService.sendMessageToConv(chatMessage, conversationId, headerAccessor);
        return chatMessage;
    }
}
