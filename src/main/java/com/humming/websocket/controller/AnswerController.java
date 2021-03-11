package com.humming.websocket.controller;

import com.humming.websocket.message.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AnswerController {
    @MessageMapping("/{concert_pk}/answer")
    @SendTo("/listen/{concert_pk}/answer")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        return new SocketMessage(message.getMessage());
    }
}