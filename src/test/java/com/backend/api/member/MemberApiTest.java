package com.backend.api.member;

import com.backend.api.MemberApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@SpringBootTest
class MemberApiTest {
    @Autowired
    MemberApi memberApi;

    @Test
    public void getSignupTest(){
        String result = memberApi.dispSignup();
    }

    /*@Test
    public void postSignupTest(){
        Long result = memberApi.execSignup();
    }

    @Test
    public void postSigninTest(){
        HashMap<String, String> result = memberApi.dispLogin();
    }

    @Test
    public void getUserTest(){
        HashMap<String, String> result = memberApi.getUserInfo();
    }*/
}
