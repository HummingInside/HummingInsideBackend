package com.humming.core.concert;

import com.humming.core.member.Member;
import com.humming.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ConcertTest {

    @Autowired ConcertRepository concertRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired CategoryRepository categoryRepository;

    @Test
    void createAndReadConcert(){
        Member performer = createMember();
        Category category = createCategory();
        Concert concert = Concert.builder()
                .title("BTS 2020 Last Concert")
                .performer(performer)
                .category(category)
                .description("This is the last concert of BTS in 2020!")
                .maxAudience(10000)
                .price(55000)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .imgUrl("testUrl")
                .build();
        Long id = concertRepository.save(concert).getId();

        String stringResult = Concert.builder().toString();
        int hashResult = concert.hashCode();

        concertRepository.findById(id).ifPresent(findConcert -> {
            boolean equalsTrue = findConcert.equals(concert);
            boolean equalsFalse = findConcert.equals(new Concert());
            assertThat(findConcert.getTitle()).isEqualTo(concert.getTitle());
            assertThat(findConcert.getPerformer()).isEqualTo(concert.getPerformer());
            assertThat(findConcert.getCategory()).isEqualTo(concert.getCategory());
            assertThat(findConcert.getCurrentAudience()).isEqualTo(concert.getCurrentAudience());
            assertThat(findConcert.getStatus()).isEqualTo(concert.getStatus());

            assertThat(findConcert.getStartDate()).isEqualTo(concert.getStartDate());
            assertThat(findConcert.getEndDate()).isEqualTo(concert.getEndDate());

            assertThat(findConcert.getDescription()).isEqualTo(concert.getDescription());
            assertThat(findConcert.getMaxAudience()).isEqualTo(concert.getMaxAudience());
            assertThat(findConcert.getPrice()).isEqualTo(concert.getPrice());
            assertThat(findConcert.getLikesCount()).isEqualTo(concert.getLikesCount());
            assertThat(findConcert.getImgUrl()).isEqualTo(concert.getImgUrl());

            System.out.println(findConcert.getCreatedDate());
            System.out.println(findConcert.getStatus());
        });
    }

    @Test
    void updateConcert(){
        Member performer = createMember();
        Category category = createCategory();
        Concert concert = Concert.builder()
                .title("BTS 2020 Last Concert")
                .performer(performer)
                .category(category)
                .description("This is the last concert of BTS in 2020!")
                .maxAudience(10000)
                .price(55000)
                .build();

        LocalDateTime time = LocalDateTime.now();

        concert.updateCategory(category);
        concert.updateInfo("testTitle", LocalDateTime.now(), LocalDateTime.now(), "testDescription",
        10, 3, 3000, "testImgUrl");
        concert.updateStatus(ConcertStatus.ENDED);
    }

    private Member createMember(){
        return memberRepository.save(Member.builder()
                .email("testEmail")
                .password("tesPass")
                .name("Junyoung")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

    private Category createCategory(){
        return categoryRepository.save(Category.builder()
                .name("K-pop")
                .build());
    }
}