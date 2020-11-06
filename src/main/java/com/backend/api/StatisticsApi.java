package com.backend.api;

import com.backend.application.dto.concert.ConcertSimpleResponse;
import com.backend.application.dto.reservation.ReservationResponse;
import com.backend.application.service.ConcertService;
import com.backend.application.service.StatisticsService;
import com.backend.application.serviceImpl.StatisticsServiceImpl;
import com.backend.core.concert.Concert;
import com.backend.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/my-page")
@RequiredArgsConstructor
public class StatisticsApi {

    private final StatisticsService statisticsService;
    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<?> getMyInfo(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        List<Object> result = new ArrayList<>();

        List<ConcertSimpleResponse> responses = statisticsService.getMyConcertList(member.getId());
        List<ReservationResponse> responses1 = statisticsService.getMyReservationList(member.getId());

        result.add(responses);
        result.add(responses1);

        return ResponseEntity.ok(result);
    }
}
