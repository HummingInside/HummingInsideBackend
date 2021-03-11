package com.humming.application.service;

import com.humming.application.dto.concert.ConcertSimpleResponse;
import com.humming.application.dto.reservation.ReservationResponse;

import java.util.List;

public interface StatisticsService {

    List<ConcertSimpleResponse> getMyConcertList(Long userId);

    List<ReservationResponse> getMyReservationList(Long userId);

    List<ReservationResponse> getMyTotalRevenue(Long userId);

    List<ReservationResponse> getConcertRevenue(Long concertId);
}
