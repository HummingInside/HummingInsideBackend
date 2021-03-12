package com.humming.api.statistics;

import com.humming.api.controller.StatisticsApi;
import com.humming.api.dto.member.JwtTokenProvider;
import com.humming.domain.Member;
import com.humming.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@SpringBootTest
public class StatisticsApiTest {
    @Autowired
    StatisticsApi statisticsApi;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    void getMyInfoTest(){
        Member member = memberRepository.findByEmail("sample1@gmail.com").get();

        String token = jwtTokenProvider.createToken(member.getEmail(), member.getRoles());

        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        statisticsApi.getMyInfo();
    }

    @Test
    void getStatistics(){

        Member member = memberRepository.findByEmail("sample1@gmail.com").get();

        String token = jwtTokenProvider.createToken(member.getEmail(), member.getRoles());

        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HashMap<String, String> concert = new HashMap<String, String>();

        concert.put("concertId", "1");

        statisticsApi.getStatistics(concert);

    }
}
