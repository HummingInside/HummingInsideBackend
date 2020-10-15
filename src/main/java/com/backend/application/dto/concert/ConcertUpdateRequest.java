package com.backend.application.dto.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Setter
public class ConcertUpdateRequest {

    private String title;
    private LocalDateTime date;
    private String description;
    private int maxAudience;
    private int price;
    private String imgUrl;
}
