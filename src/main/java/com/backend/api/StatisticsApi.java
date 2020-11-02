package com.backend.api;

import com.backend.application.service.ConcertService;
import com.backend.application.service.StatisticsService;
import com.backend.application.serviceImpl.StatisticsServiceImpl;
import com.backend.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/my-page")
@RequiredArgsConstructor
public class StatisticsApi {

    private final StatisticsService statisticsService;
    private final ConcertService concertService;

    @GetMapping
    public HashMap<String, String> getMyInfo(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        HashMap<String, String> result = new HashMap<String, String>();

        result = statisticsService.getMyConcertList(member.getId());

        return null;
    }
}
