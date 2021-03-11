package com.humming.application.dto.concert;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class ConcertListRequest {

    private String concertStatus;
    private Long categoryId;
    private String performerName;
    // TODO : make pageable
}
