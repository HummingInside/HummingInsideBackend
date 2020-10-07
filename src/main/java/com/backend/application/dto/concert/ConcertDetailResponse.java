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
public class ConcertDetailResponse extends ConcertSimpleResponse {

    private CategorySimple category;
    private ConcertStatus status;

    public ConcertDetailResponse(Concert concert){
        super(concert);
        category = new CategorySimple(concert.getCategory());
        status = concert.getStatus();
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