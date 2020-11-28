package com.backend.websocket.controller;

import com.backend.application.serviceImpl.ConcertServiceImpl;
import com.backend.core.concert.ConcertStatus;
import com.backend.websocket.message.SocketMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class PerformerEnterController {

    private final ConcertServiceImpl concertService;

    @MessageMapping("/{concert_pk}/performer/enter")
    @SendTo("/listen/{concert_pk}/performer/enter")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        Long pk = Long.parseLong((String) message.getMessage().get("pk"));
        concertService.updateStatus(pk, ConcertStatus.ONAIR);
        return new SocketMessage(message.getMessage());
    }
}