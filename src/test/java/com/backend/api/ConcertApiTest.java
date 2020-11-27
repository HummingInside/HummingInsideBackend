package com.backend.api;

import com.backend.WithUser;
import com.backend.application.dto.concert.ConcertCreateRequest;
import com.backend.application.dto.concert.ConcertDetailResponse;
import com.backend.application.dto.concert.ConcertSimpleResponse;
import com.backend.core.concert.Category;
import com.backend.core.concert.CategoryRepository;
import com.backend.core.concert.ConcertStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ConcertApiTest {

    @Autowired MockMvc mockMvc;
    @Autowired CategoryRepository categoryRepository;

    final String memberName = "user1";
    final String memberEmail = "sample@gmail.com";

    @Test
    @WithUser(name = memberName, email = memberEmail)
    @DisplayName("Get concert list")
    void getConcertList() throws Exception {
        MvcResult result = mockMvc.perform(get("/concerts"))
                .andExpect(status().isOk()).andReturn();
        List<ConcertSimpleResponse> response = getMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<ConcertSimpleResponse>>(){});
        assertThat(response.size()).isGreaterThan(0);
    }

    @Test
    @WithUser(name = memberName, email = memberEmail)
    @DisplayName("Create a concert")
    void createConcert() throws Exception {
        Category category = getCategory();
        ConcertCreateRequest request = new ConcertCreateRequest();
        request.setTitle("Sample Concert");
        request.setCategoryId(category.getId());
        request.setDescription("sample description");
        request.setPrice(30000);
        request.setMaxAudience(1000);
        request.setStartDate(LocalDateTime.now());
        request.setEndDate(LocalDateTime.now().plusDays(1));

        MvcResult result = mockMvc.perform(post("/concerts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();
        ConcertDetailResponse response = getMapper().readValue(
                result.getResponse().getContentAsString(),
                ConcertDetailResponse.class);

        assertThat(response.getTitle()).isEqualTo(request.getTitle());
        assertThat(response.getCategory().getId()).isEqualTo(category.getId());
        assertThat(response.getDescription()).isEqualTo(request.getDescription());
        assertThat(response.getPrice()).isEqualTo(request.getPrice());
        assertThat(response.getStartDate()).isEqualTo(request.getStartDate());
        assertThat(response.getEndDate()).isEqualTo(request.getEndDate());
        assertThat(response.isHasPurchased()).isEqualTo(false);
        assertThat(response.isHasOwnership()).isEqualTo(true);
        assertThat(response.getPerformer()).isEqualTo(memberName);
        assertThat(response.getMaxAudience()).isEqualTo(request.getMaxAudience());
        assertThat(response.getCurrentAudience()).isEqualTo(0);
        assertThat(response.getStatus()).isEqualTo(ConcertStatus.UPCOMING.getDesc());
    }

    private ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
    private Category getCategory(){
        return categoryRepository.save(Category.builder()
                .name("sample category")
                .build());
    }
}