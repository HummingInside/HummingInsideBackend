package com.humming.application.dto.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Setter
public class ConcertUpdateRequest {

    private String title;
    private Long categoryId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private int maxAudience;
    private int currentAudience;
    private int price;
    private String imgUrl;
}
