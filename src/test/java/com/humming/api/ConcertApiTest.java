package com.humming.api;

import com.humming.WithUser;
import com.humming.api.dto.concert.ConcertCreateRequest;
import com.humming.api.dto.concert.ConcertDetailResponse;
import com.humming.api.dto.concert.ConcertSimpleResponse;
import com.humming.api.dto.concert.ConcertUpdateRequest;
import com.humming.domain.Category;
import com.humming.domain.Concert;
import com.humming.domain.Member;
import com.humming.repository.CategoryRepository;
import com.humming.repository.ConcertRepository;
import com.humming.repository.MemberRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.humming.domain.ConcertStatus;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ConcertApiTest {

    @Autowired MockMvc mockMvc;
    @Autowired
    ConcertRepository concertRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired MemberRepository memberRepository;

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

    @Test
    @WithUser(name = memberName, email = memberEmail)
    @DisplayName("Delete a concert")
    void deleteConcert() throws Exception {
        Concert concert = getConcert();

        String url = "/concerts/" + concert.getId();
        MvcResult result = mockMvc.perform(delete(url))
                .andExpect(status().isOk()).andReturn();
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUser(name = memberName, email = memberEmail)
    @DisplayName("Update a concert")
    void updateConcert() throws Exception {
        Concert concert = getConcert();

        String title = concert.getTitle();
        Category category = getCategory();
        ConcertUpdateRequest request = new ConcertUpdateRequest();
        request.setTitle("Sample Concert");
        request.setCategoryId(category.getId());
        request.setDescription("sample description");
        request.setPrice(30000);
        request.setMaxAudience(1000);
        request.setStartDate(LocalDateTime.now());
        request.setEndDate(LocalDateTime.now().plusDays(1));

        String url = "/concerts/" + concert.getId();

        MvcResult updateResult = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();
        ConcertDetailResponse response = getMapper().readValue(
                updateResult.getResponse().getContentAsString(),
                ConcertDetailResponse.class);

        assertThat(response.getTitle())
                .isEqualTo(request.getTitle())
                .isNotEqualTo(title);
    }

    private Member getMember(){
        return memberRepository.findByEmail(memberEmail).get();
    }

    private Concert getConcert(){
        return concertRepository.save(Concert.builder()
                .title("BTS 2020 Last Concert")
                .performer(getMember())
                .category(getCategory())
                .description("This is the last concert of BTS in 2020!")
                .maxAudience(10000)
                .price(55000)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .imgUrl("testUrl")
                .build());
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