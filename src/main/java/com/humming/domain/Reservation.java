package com.humming.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Builder
@Entity
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private Long concertId;

    @Column(length = 20, nullable = false)
    private Long performerId;

    @Column(length = 100, nullable = false, updatable = false)
    private int purchaseCount;

    @Column(length = 100, nullable = false, updatable = false)
    private Long purchaseId;

    public void countUp(){
        purchaseCount++;
    }
}

