package com.demo.ChatApplication.controller;

import com.demo.ChatApplication.model.Chat;
import com.demo.ChatApplication.model.ChatMessage;
import com.demo.ChatApplication.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @MessageMapping("/sendMessage") // Maps to /app/sendMessage
    @SendTo("/topic/messages") // Sends to /topic/messages
    public ChatMessage sendMessage(ChatMessage message) {

        chatRepository.save(new Chat(message.getSender(), message.getReceiver(), message.getContent(), message.getTimestamp()));
        return message;
    }
}
