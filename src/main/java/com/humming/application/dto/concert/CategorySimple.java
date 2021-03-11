package com.humming.application.dto.concert;

import com.humming.core.concert.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class CategorySimple {
    private Long id;
    private String name;

    public CategorySimple(Category category){
        id = category.getId();
        name = category.getName();
    }
}
