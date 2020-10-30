package com.backend.core.concert;

import com.backend.core.member.Member;
import com.backend.core.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ConcertTest {

    @Autowired ConcertRepository concertRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired CategoryRepository categoryRepository;

    @Test
    @DisplayName("create and read a concert")
    void createAndReadConcert(){
        Member performer = createMember();
        Category category = createCategory();
        Concert concert = Concert.builder()
                .title("BTS 2020 Last Concert")
                .performer(performer)
                .category(category)
//                .date(LocalDateTime.of(2020, 12, 29, 22, 0, 0))
                .description("This is the last concert of BTS in 2020!")
                .maxAudience(10000)
                .price(55000)
                .build();
        Long id = concertRepository.save(concert).getId();

        concertRepository.findById(id).ifPresent(findConcert -> {
            assertThat(findConcert.getTitle()).isEqualTo(concert.getTitle());
            assertThat(findConcert.getPerformer()).isEqualTo(performer);
            assertThat(findConcert.getCategory()).isEqualTo(category);
            System.out.println(findConcert.getCreatedDate());
            System.out.println(findConcert.getStatus());
        });

    }

    private Member createMember(){
        return memberRepository.save(Member.builder().name("Junyoung").build());
    }

    private Category createCategory(){
        return categoryRepository.save(Category.builder().name("K-pop").build());
    }
}