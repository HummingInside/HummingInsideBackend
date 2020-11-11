package com.backend.application.serviceImpl;

import com.backend.application.dto.concert.ConcertSimpleResponse;
import com.backend.application.dto.reservation.ReservationResponse;
import com.backend.application.service.StatisticsService;
import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertRepository;
import com.backend.core.reservation.Reservation;
import com.backend.core.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ReservationRepository reservationRepository;
    private final ConcertRepository concertRepository;

    @Override
    public List<ConcertSimpleResponse>getMyConcertList(Long userId) {

        return concertRepository.findAllByPerformer_Id(userId);
    }

    @Override
    public List<ReservationResponse> getMyReservationList(Long userId) {
        List<Object[]> objects = reservationRepository.myReservations(userId);
        return getReservationResponses(objects);
    }

    @Override
    public List<ReservationResponse> getMyTotalRevenue(Long userId) {
        List<Object[]> objects = reservationRepository.myTotalRevenue(userId);
        return getReservationResponses(objects);
    }

    @Override
    public List<ReservationResponse> getConcertRevenue(Long concertId) {
        List<Object[]> objects = reservationRepository.myConcertRevenue(concertId);
        return getReservationResponses(objects);
    }

    private List<ReservationResponse> getReservationResponses(List<Object[]> objects) {
        List<ReservationResponse> result = new ArrayList<>();

        for(int i=0;i<objects.size();i++){
            ReservationResponse response = new ReservationResponse(objects.get(i));
            result.add(response);
        }

        return result;
    }
}
