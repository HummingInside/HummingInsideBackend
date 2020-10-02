package com.backend.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersApi.class)
public class UsersApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void signInTest() throws Exception{

        mockMvc.perform(post("/signin")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void signOutTest() throws Exception{

        mockMvc.perform(post("/signout")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void signUpTest() throws Exception{

        mockMvc.perform(post("/signup")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void userInfoTest() throws Exception{

        mockMvc.perform(post("/userinfo")).andExpect(status().isOk()).andDo(print());
    }
}