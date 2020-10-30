package com.backend.api;

import com.backend.application.dto.member.JwtTokenProvider;
import com.backend.application.service.MemberService;
import com.backend.core.member.Member;
import com.backend.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApi {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    private final MemberService memberService;

    @GetMapping("/signup")
    public String dispSignup() {
        return "/signup";
    }

    @PostMapping("/signup")
    public Long execSignup(@RequestBody Map<String, String> member) {
        return memberRepository.save(Member.builder()
                .email(member.get("email"))
                .name(member.get("name"))
                //.password(passwordEncoder.encode(member.get("password")))
                .password(member.get("password"))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    @PostMapping("/signin")
    public HashMap<String, String> dispLogin(@RequestBody Map<String, String> user) {
        HashMap<String, String> result = new HashMap<String, String>();

        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!user.get("password").equals(member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        result.put("id", member.getId().toString());
        result.put("username", member.getUsername());
        result.put("email", member.getEmail());
        result.put("role", member.getRoles().toString());
        result.put("token", jwtTokenProvider.createToken(member.getEmail(), member.getRoles()));

        return result;
        //return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    @GetMapping("/user")
    public HashMap<String, String> getUserInfo() {

        HashMap<String, String> result = new HashMap<String, String>();
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        result.put("id", member.getId().toString());
        result.put("username", member.getUsername());
        result.put("email", member.getEmail());
        result.put("role", member.getRoles().toString());
        result.put("token", jwtTokenProvider.createToken(member.getEmail(), member.getRoles()));

        return result;
    }

}
