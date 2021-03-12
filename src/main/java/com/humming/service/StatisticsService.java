package com.humming.service;

import com.humming.api.dto.concert.ConcertSimpleResponse;
import com.humming.api.dto.reservation.ReservationResponse;
import com.humming.repository.ConcertRepository;
import com.humming.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private final ReservationRepository reservationRepository;
    private final ConcertRepository concertRepository;

    public List<ConcertSimpleResponse>getMyConcertList(Long userId) {

        return concertRepository.findAllByPerformer_Id(userId);
    }

    public List<ReservationResponse> getMyReservationList(Long userId) {
        List<Object[]> objects = reservationRepository.myReservations(userId);
        return getReservationResponses(objects);
    }

    public List<ReservationResponse> getMyTotalRevenue(Long userId) {
        List<Object[]> objects = reservationRepository.myTotalRevenue(userId);
        return getReservationResponses(objects);
    }

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
