package com.backend.api;

import com.backend.WithUser;
import com.backend.application.dto.concert.CategorySimple;
import com.backend.application.dto.concert.ConcertSimpleResponse;
import com.backend.core.concert.CategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class CategoryApiTest {

    @Autowired MockMvc mockMvc;
    @Autowired CategoryRepository categoryRepository;

    final String memberName = "user1";
    final String memberEmail = "sample@gmail.com";

    @Test
    @WithUser(name = memberName, email = memberEmail)
    @DisplayName("Get category list")
    void getConcertList() throws Exception {
        MvcResult result = mockMvc.perform(get("/categories"))
                .andExpect(status().isOk()).andReturn();
        List<CategorySimple> response = getMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<CategorySimple>>(){});
        assertThat(response.size()).isGreaterThan(0);
    }

    @Test
    @WithUser(name = memberName, email = memberEmail)
    @DisplayName("Get a category")
    void getConcert() throws Exception {
        MvcResult result = mockMvc.perform(get("/categories/51"))
                .andExpect(status().isOk()).andReturn();
        CategorySimple response = getMapper().readValue(
                result.getResponse().getContentAsString(),
                CategorySimple.class);
        assertThat(response.getName()).isNotBlank();
    }

    private ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}