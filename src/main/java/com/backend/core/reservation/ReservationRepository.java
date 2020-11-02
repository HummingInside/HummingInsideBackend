package com.backend.core.reservation;

import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertStatus;
import com.backend.core.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT c FROM Concert c WHERE (c.performer.id = :userId)")
    List<Concert> findMyConcertList(@Param("userId") Long userId);

    Reservation findByPurchaseId(Long purchaseId);
}
