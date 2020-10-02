package com.backend.core.user;

import com.backend.core.concert.Category;
import com.backend.core.concert.Concert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("create and read a user")
    void createAndReadUser(){
        User user = User.builder()
                .id("testID")
                .password("1234")
                .email("test@email.com")
                .name("testName")
                .build();
        Long id = userRepository.save(user).getId();

        userRepository.findById(id).ifPresent(findUser -> {
            assertThat(findUser.getUserId()).isEqualTo(user.getUserId());
            assertThat(findUser.getUserPassword()).isEqualTo(user.getUserPassword());
            assertThat(findUser.getUserEmail()).isEqualTo(user.getUserEmail());
            assertThat(findUser.getUserName()).isEqualTo(user.getUserName());
            System.out.println(findUser.getUserId());
            System.out.println(findUser.getUserName());
        });

    }
}
