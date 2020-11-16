package com.backend.websocket.controller;

import com.backend.websocket.message.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/{concert_pk}/chat")
    @SendTo("/listen/{concert_pk}/chat")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        return new SocketMessage(message.getMessage());
    }
}