package com.humming.core.concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CategoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    void createCategoryTest(){
        Category category = Category.builder()
                .name("test")
                .build();

    }
}
