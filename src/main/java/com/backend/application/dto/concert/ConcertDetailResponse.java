package com.backend.application.dto.concert;

import com.backend.core.concert.Category;
import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
@Getter
public class ConcertDetailResponse extends ConcertSimpleResponse {

    private CategorySimple category;
    private int price;
    private String description;
    private LocalDateTime endDate;
    private String runningTime;

    public ConcertDetailResponse(Concert concert){
        super(concert);
        category = new CategorySimple(concert.getCategory());
        price = concert.getPrice();
        description = concert.getDescription();
        endDate = concert.getEndDate();
//                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd / HH:mm"));
        Duration duration = Duration.between(concert.getStartDate(), concert.getEndDate());
        String hours = duration.toHours() > 0 ? duration.toHours()+" hours" : "";
        String minutes = duration.toMinutes() > 0 ? duration.toMinutes()+" minutes" : "";
        runningTime = hours + (hours.length() > 0 && minutes.length() > 0 ? " and " : "") + minutes;
    }
}

@Setter
@NoArgsConstructor
@Getter
class CategorySimple {
    private Long id;
    private String name;

    public CategorySimple(Category category){
        id = category.getId();
        name = category.getName();
    }
}