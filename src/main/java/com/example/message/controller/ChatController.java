package com.example.message.controller;

import com.example.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/*
This controller is responsible For:
 - receiving messages from one client
 - broadcasting it to others
*/
@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/public")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSendToUser(
                "34" ,"/public", chatMessage
        );
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("CONNECTED");
        System.out.println(chatMessage);
        return chatMessage;
    }
}
