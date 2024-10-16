package org.ichat.backend.services.social;

import org.ichat.backend.model.tables.social.ChatMessage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface IChatService {

    void sendMessageToConv(ChatMessage chatMessage, Long conversationId, SimpMessageHeaderAccessor headerAccessor);
}
