package com.backend.application.dto.concert;

import com.backend.core.concert.Concert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
@Getter
public class ConcertSimpleResponse {
    private Long id;
    private String title;
    private String performer;
    private LocalDateTime startDate;
    private int likesCount;
    private String status;
    private int currentAudience;
    private int maxAudience;
    private String imgUrl;

    public ConcertSimpleResponse(Concert concert){
        id = concert.getId();
        title = concert.getTitle();
        performer = concert.getPerformer().getName();
        startDate = concert.getStartDate();
//                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd / HH:mm"));
        status = concert.getStatus().getDesc();
        likesCount = concert.getLikesCount();
        currentAudience = concert.getCurrentAudience();
        maxAudience = concert.getMaxAudience();
        imgUrl = concert.getImgUrl();
    }
}
