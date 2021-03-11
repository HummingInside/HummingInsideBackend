package com.humming.websocket.message;

import lombok.*;

import java.util.LinkedHashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocketMessage {
    private LinkedHashMap message;
}
