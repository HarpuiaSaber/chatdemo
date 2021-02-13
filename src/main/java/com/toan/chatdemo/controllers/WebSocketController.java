package com.toan.chatdemo.controllers;

import com.toan.chatdemo.models.dtos.ChatMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessageDto sendMessage(@Payload ChatMessageDto chatMessageDto) {
        return chatMessageDto;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessageDto addUser(@Payload ChatMessageDto chatMessageDto, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessageDto.getSender());
        logger.info(chatMessageDto.getSender() + " connected");
        return chatMessageDto;
    }
}
