package com.backend.core.reservation;

import com.backend.application.dto.reservation.ReservationResponse;
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

    //List<ReservationResponse> findAllByPurchaseId(Long userId);

    @Query("SELECT r.id, r.concertId, r.purchaseId, r.purchaseCount, c.startDate, c.title, c.imgUrl, c.price, r.createdDate"+
            " FROM Reservation r INNER JOIN Concert c ON r.concertId = c.id WHERE r.purchaseId = :userId")
    List<Object[]> myReservations(@Param("userId") Long userId);

    @Query("SELECT r.id, r.concertId, r.purchaseId, r.purchaseCount, c.startDate, c.title, c.imgUrl, c.price, r.createdDate"+
            " FROM Reservation r INNER JOIN Concert c ON r.concertId = c.id WHERE c.performer.id = :userId")
    List<Object[]> myTotalRevenue(@Param("userId") Long userId);

    @Query("SELECT r.id, r.concertId, r.purchaseId, r.purchaseCount, c.startDate, c.title, c.imgUrl, c.price, r.createdDate"+
            " FROM Reservation r INNER JOIN Concert c ON r.concertId = c.id WHERE r.concertId = :concertId")
    List<Object[]> myConcertRevenue(@Param("concertId") Long concertId);

    Optional<Reservation> findFirstByConcertIdAndPurchaseId(@Param("concertId") Long concertId,
                                                  @Param("purchaseId") Long PurchaseId);
}
