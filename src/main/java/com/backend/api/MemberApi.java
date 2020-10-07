package com.backend.api;

import com.backend.application.dto.member.MemberCreateRequest;
import com.backend.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    
    private final MemberService memberService;

    @GetMapping
    public String index() {
        return "/index";
    }

    @GetMapping("/signup")
    public String dispSignup() {
        return "/signup";
    }

    @PostMapping("/signup")
    public String execSignup(MemberCreateRequest request) {
        memberService.joinUser(request);
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String dispLogin() {
        return "/signin";
    }

    @GetMapping("/signin/result")
    public String dispLoginResult() {
        return "/loginSuccess";
    }

    @GetMapping("/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    @GetMapping("/denied")
    public String dispDenied() {
        return "/denied";
    }

    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
}
