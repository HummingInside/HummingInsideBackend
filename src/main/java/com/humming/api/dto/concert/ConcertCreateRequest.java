package com.humming.api.dto.concert;

import com.humming.domain.Category;
import com.humming.domain.Concert;
import com.humming.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@Getter
public class ConcertCreateRequest {
    private String title;
    private Long categoryId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private int maxAudience;
    private int price;
    private String imgUrl;

    public Concert toEntity(Member member, Category category){
        return Concert.builder()
                .title(title)
                .performer(member)
                .category(category)
                .startDate(startDate)
                .endDate(endDate)
                .imgUrl(imgUrl)
                .description(description)
                .maxAudience(maxAudience)
                .price(price).build();
    }
}
