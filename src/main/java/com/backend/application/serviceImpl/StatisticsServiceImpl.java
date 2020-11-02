package com.backend.application.serviceImpl;

import com.backend.application.service.StatisticsService;
import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertRepository;
import com.backend.core.reservation.Reservation;
import com.backend.core.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {

    ReservationRepository reservationRepository;
    ConcertRepository concertRepository;

    @Override
    public HashMap<String, String> getMyConcertList(Long userId) {

        HashMap<String, String> result = new HashMap<String, String>();

        System.out.println(userId);

        //Reservation myConcert;
        List<Concert> myConcert;

        //result = reservationRepository.findMyConcertList(userId);
        //List<Concert> myConcert = reservationRepository.findMyConcertList(userId);

        //List<Concert> myConcert = concertRepository.findByPerformerId(userId);
        try{
            myConcert = concertRepository.findAllByPerformer_Id(userId);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("myConcert");

        return result;
    }
}
