package com.humming.api.dto.concert;

import com.humming.domain.Concert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@Getter
public class ConcertDetailResponse extends ConcertSimpleResponse {

    private CategorySimple category;
    private int price;
    private String description;
    private LocalDateTime endDate;
    private String runningTime;
    private boolean hasPurchased;
    private boolean hasOwnership;

    public ConcertDetailResponse(Concert concert, boolean hasPurchased, boolean hasOwnership){
        super(concert);
        category = new CategorySimple(concert.getCategory());
        price = concert.getPrice();
        description = concert.getDescription();
        endDate = concert.getEndDate();
//                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd / HH:mm"));
        Duration duration = Duration.between(concert.getStartDate(), concert.getEndDate());
        String hours = duration.toHours() > 0 ? duration.toHours()+" hours" : "";
        String minutes = duration.toMinutes() > 0 ? duration.toMinutes()%60+" minutes" : "";
        runningTime = hours + (hours.length() > 0 && minutes.length() > 0 ? " and " : "") + minutes;
        this.hasPurchased = hasPurchased;
        this.hasOwnership = hasOwnership;
    }
}