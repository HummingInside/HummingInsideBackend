package com.humming.websocket.controller;

import com.humming.websocket.message.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class OfferController {
    @MessageMapping("/{concert_pk}/offer")
    @SendTo("/listen/{concert_pk}/offer")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        return new SocketMessage(message.getMessage());
    }
}