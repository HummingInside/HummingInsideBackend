package com.backend.application.dto.reservation;

import com.backend.core.reservation.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
@Getter
public class ReservationResponse {

    private Long id;

    private Long concertId;

    private int purchaseCount;

    private Long purchaseId;

    private String startDate;

    private String imgUrl;

    private String title;

    private int price;

    private String createdDate;

    /*public ReservationResponse(Long id, Long concertId, Long purchaseId, int purchaseCount,
                               LocalDateTime startDate, String title, String imgUrl, int price){
        this.id = id;
        this.concertId = concertId;
        this.purchaseId = purchaseId;
        this.purchaseCount = purchaseCount;
        this.startDate = startDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd / HH:mm"));
        this.title = title;
        this.imgUrl = imgUrl;
        this.price = price;
    }*/

    public ReservationResponse(Object[] objects){
        this.id = (Long) objects[0];
        this.concertId = (Long) objects[1];
        this.purchaseId = (Long) objects[2];
        this.purchaseCount = (int) objects[3];
        LocalDateTime date = (LocalDateTime) objects[4];
        this.startDate = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd / HH:mm"));
        this.title = (String) objects[5];
        this.imgUrl = (String) objects[6];
        this.price = (int) objects[7];
        date = (LocalDateTime) objects[8];
        this.createdDate = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
