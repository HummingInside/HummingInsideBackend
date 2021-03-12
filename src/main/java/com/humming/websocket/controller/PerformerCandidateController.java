package com.humming.websocket.controller;

import com.humming.websocket.message.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PerformerCandidateController {
    @MessageMapping("/{concert_pk}/performer_candidate")
    @SendTo("/listen/{concert_pk}/performer_candidate")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        return new SocketMessage(message.getMessage());
    }
}