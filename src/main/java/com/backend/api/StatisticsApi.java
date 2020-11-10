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
}
