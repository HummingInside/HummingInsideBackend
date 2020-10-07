package com.backend.api;

import com.backend.application.dto.member.MemberDto;
import com.backend.application.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
//@Controller
@AllArgsConstructor
public class MemberApi {

    @Autowired
    private MemberService memberService;

    @RequestMapping(path = "/", method = GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(path = "/user/signup", method = GET)
    public String dispSignup() {
        return "/signup";
    }

    @RequestMapping(path = "/user/signup", method = POST)
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    @RequestMapping(path = "/user/login", method = GET)
    public String dispLogin() {
        return "/login";
    }

    @RequestMapping(path = "/user/login/result", method = GET)
    public String dispLoginResult() {
        return "/loginSuccess";
    }

    @RequestMapping(path = "/user/logout/result", method = GET)
    public String dispLogout() {
        return "/logout";
    }

    @RequestMapping(path = "/user/denied", method = GET)
    public String dispDenied() {
        return "/denied";
    }

    @RequestMapping(path = "/admin", method = GET)
    public String dispAdmin() {
        return "/admin";
    }
}
