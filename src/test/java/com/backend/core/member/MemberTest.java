package com.backend.core.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void createAndReadMember(){
        Member member = Member.builder()
                .email("testEmail@gmail.com")
                .name("testName")
                .password("testPassword")
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        String objectString = Member.builder().toString();

        boolean enableResult = member.isEnabled();
        boolean expiredResult = member.isAccountNonExpired();
        boolean lockedResult = member.isAccountNonLocked();
        boolean credentialsResult = member.isCredentialsNonExpired();
        String userNameResult = member.getUsername();
        int hashCodeResult = member.hashCode();

        memberRepository.save(member);

        memberRepository.findByEmail(member.getEmail()).ifPresent(findMember -> {
            boolean equalsResult = findMember.equals(member);
            System.out.println(equalsResult);
            boolean equalsResultF = findMember.equals(new Member());
            System.out.println(equalsResultF);
            assertThat(findMember.getId()).isEqualTo(member.getId());
            assertThat(findMember.getName()).isEqualTo(member.getName());
            assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
            assertThat(findMember.getRoles()).isEqualTo(member.getRoles());
        });
    }
}
