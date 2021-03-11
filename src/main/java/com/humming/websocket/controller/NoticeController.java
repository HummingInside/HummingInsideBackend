package com.humming.websocket.controller;

import com.humming.websocket.message.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NoticeController {
    @MessageMapping("/{concert_pk}/notice")
    @SendTo("/listen/{concert_pk}/notice")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        return new SocketMessage(message.getMessage());
    }
}