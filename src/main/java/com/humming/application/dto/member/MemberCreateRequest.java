package com.humming.application.dto.member;

import com.humming.core.member.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberCreateRequest {

    private Long id;
    private String email;
    private String password;
    private String name;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

    @Builder
    public MemberCreateRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
