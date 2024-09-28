package org.ichat.backend.services.social.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.ChatMessage;
import org.ichat.backend.model.tables.social.Converstation;
import org.ichat.backend.services.social.IChatService;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService implements IChatService {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
//    private final ConversationRepository conversationRepository;
//    private final IOnlineOfflineService onlineOfflineService;

    public void sendMessageToConv(
            ChatMessage chatMessage, Long conversationId, SimpMessageHeaderAccessor headerAccessor) {
//        User user = getUser();
//        var fromUserId = user.getUser_id();
//        var toUserId = chatMessage.getReceiverId();
//        populateContext(chatMessage, userDetails);
//        boolean isTargetOnline = onlineOfflineService.isUserOnline(toUserId);
//        boolean isTargetSubscribed =
//                onlineOfflineService.isUserSubscribed(toUserId, "/topic/" + conversationId);
//        chatMessage.setId(UUID.randomUUID());
//
//        Converstation conversation = new Converstation();
//
//        conversationEntityBuilder
//                .id(chatMessage.getId())
//                .fromUser(fromUserId)
//                .toUser(toUserId)
//                .content(chatMessage.getContent())
//                .convId(conversationId);
//        if (!isTargetOnline) {
//            log.info(
//                    "{} is not online. Content saved in unseen messages", chatMessage.getReceiverUsername());
//            conversationEntityBuilder.deliveryStatus(MessageDeliveryStatusEnum.NOT_DELIVERED.toString());
//            chatMessage.setMessageDeliveryStatusEnum(MessageDeliveryStatusEnum.NOT_DELIVERED);
//
//        } else if (!isTargetSubscribed) {
//            log.info(
//                    "{} is online but not subscribed. sending to their private subscription",
//                    chatMessage.getReceiverUsername());
//            conversationEntityBuilder.deliveryStatus(MessageDeliveryStatusEnum.DELIVERED.toString());
//            chatMessage.setMessageDeliveryStatusEnum(MessageDeliveryStatusEnum.DELIVERED);
//            simpMessageSendingOperations.convertAndSend("/topic/" + toUserId.toString(), chatMessage);
//
//        } else {
//            conversationEntityBuilder.deliveryStatus(MessageDeliveryStatusEnum.SEEN.toString());
//            chatMessage.setMessageDeliveryStatusEnum(MessageDeliveryStatusEnum.SEEN);
//        }
//        conversationRepository.save(conversationEntityBuilder.build());
//        simpMessageSendingOperations.convertAndSend("/topic/" + conversationId, chatMessage);
    }

//    private void populateContext(ChatMessage chatMessage, UserDetailsImpl userDetails) {
//        chatMessage.setSenderUsername(userDetails.getUsername());
//        chatMessage.setSenderId(userDetails.getId());
//    }

    public User getUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) object;
    }
}
