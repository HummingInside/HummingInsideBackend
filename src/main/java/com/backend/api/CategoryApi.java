package com.backend.api;

import com.backend.application.dto.concert.CategorySimple;
import com.backend.core.concert.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryApi {

    private final CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        CategorySimple response = new CategorySimple(
                categoryRepository.getOne(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getList(){
        List<CategorySimple> responses = categoryRepository.findAll().stream()
                .map(CategorySimple::new)
                .collect(toList());
        return ResponseEntity.ok(responses);
    }
}
