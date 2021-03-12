package com.humming.websocket.controller;

import com.humming.domain.ConcertStatus;
import com.humming.service.ConcertService;
import com.humming.websocket.message.SocketMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class PerformerOutController {

    private final ConcertService concertService;

    @MessageMapping("/{concert_pk}/performer/out")
    @SendTo("/listen/{concert_pk}/performer/out")
    public SocketMessage forwarding(SocketMessage message) throws Exception {
        Long pk = Long.parseLong((String) message.getMessage().get("pk"));
        concertService.updateStatus(pk, ConcertStatus.ENDED);
        return new SocketMessage(message.getMessage());
    }
}