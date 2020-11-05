package com.backend.application.service;

import com.backend.application.dto.concert.ConcertSimpleResponse;
import com.backend.application.dto.reservation.ReservationResponse;
import com.backend.core.concert.Concert;

import java.util.HashMap;
import java.util.List;

public interface StatisticsService {

    List<ConcertSimpleResponse> getMyConcertList(Long userId);

    List<ReservationResponse> getMyReservationList(Long userId);
}
