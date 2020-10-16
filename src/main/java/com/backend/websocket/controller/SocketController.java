package com.backend.websocket.controller;

import com.backend.websocket.message.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/{concert_pk}")
    @SendTo("/listen/{concert_pk}")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        return new SocketMessage(message.getMessage());
    }
}