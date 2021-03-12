package com.humming.api.controller;

import com.humming.api.dto.concert.ConcertSimpleResponse;
import com.humming.api.dto.reservation.ReservationResponse;
import com.humming.domain.Member;
import com.humming.service.ConcertService;
import com.humming.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        List<ConcertSimpleResponse> myConcertResponses = statisticsService.getMyConcertList(member.getId());
        List<ReservationResponse> reservationResponses = statisticsService.getMyReservationList(member.getId());
        List<ReservationResponse> totalRevenueResponses = statisticsService.getMyTotalRevenue(member.getId());
        List<ReservationResponse> concertRevenueResponses = statisticsService.getConcertRevenue(myConcertResponses.get(0).getId());

        result.add(myConcertResponses);
        result.add(reservationResponses);
        result.add(totalRevenueResponses);
        result.add(concertRevenueResponses);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/statistics")
    public ResponseEntity<?> getStatistics(@RequestBody Map<String, String> concert){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        List<Object> result = new ArrayList<>();

        List<ReservationResponse> concertRevenueResponses = statisticsService.getConcertRevenue(Long.valueOf(concert.get("concertId")));

        result.add(concertRevenueResponses);

        return ResponseEntity.ok(result);
    }
}
