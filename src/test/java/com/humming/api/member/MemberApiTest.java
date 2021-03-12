package com.humming.api.member;

import com.humming.api.controller.MemberApi;
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
import java.util.Map;

@Transactional
@SpringBootTest
class MemberApiTest {
    @Autowired
    MemberApi memberApi;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    public void postSignupTest(){
        Map<String, String> member = new HashMap<String, String>();
        member.put("email", "test@gmail.com");
        member.put("name", "testName");
        member.put("password", "1234");

        Long result = memberApi.execSignup(member);
    }

    @Test
    public void postSigninTest(){
        HashMap<String, String> result = new HashMap<String, String>();

        Map<String, String> user = new HashMap<String, String>();
        user.put("email", "sample1@gmail.com");
        user.put("name", "Psy");
        user.put("password", "1q2w3e4r");

        result = memberApi.dispLogin(user);
    }

    @Test
    public void getUserTest(){
        HashMap<String, String> result = new HashMap<String, String>();

        Member member = memberRepository.findByEmail("sample1@gmail.com").get();

        String token = jwtTokenProvider.createToken(member.getEmail(), member.getRoles());

        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        result = memberApi.getUserInfo();
    }
}
