package com.humming.core.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class ReservationTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void createAndReadReservation(){
        Reservation reservation = Reservation.builder()
                .concertId((long) 1)
                .performerId((long) 101)
                .purchaseCount(1)
                .purchaseId((long) 107)
                .build();

        String stringResult = Reservation.builder().toString();
        int hashResult = reservation.hashCode();

        reservationRepository.save(reservation);

        reservationRepository.findById(reservation.getId()).ifPresent(findReservation -> {
            boolean equalsTrue = findReservation.equals(reservation);
            boolean equalsFalse = findReservation.equals(new Reservation());
            assertThat(findReservation.getConcertId()).isEqualTo(reservation.getConcertId());
            assertThat(findReservation.getPerformerId()).isEqualTo(reservation.getPerformerId());
            assertThat(findReservation.getPurchaseId()).isEqualTo(reservation.getPurchaseId());
            assertThat(findReservation.getPurchaseCount()).isEqualTo(reservation.getPurchaseCount());
        });
    }
}
