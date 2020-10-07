package com.backend.application.dto.concert;

import com.backend.core.concert.Category;
import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@Getter
public class ConcertSimpleResponse {
    private Long id;
    private String title;
    private String performer;
    private LocalDateTime date;
    private String description;
    private int maxAudience;
    private int price;
    private int likesCount;

    public ConcertSimpleResponse(Concert concert){
        id = concert.getId();
        title = concert.getTitle();
        performer = concert.getPerformer().getUserName();
        date = concert.getDate();
        description = concert.getDescription();
        maxAudience = concert.getMaxAudience();
        price = concert.getPrice();
        likesCount = concert.getLikesCount();
    }
}
