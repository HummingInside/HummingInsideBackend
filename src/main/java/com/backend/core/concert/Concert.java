package com.backend.core.concert;

import com.backend.core.BaseTimeEntity;
import com.backend.core.user.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
public class Concert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User performer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ConcertStatus status;

    private LocalDateTime date;

    private String description;

    private int maxAudience;

    private int price;

    private int likesCount;

    @Builder
    public Concert(String title, User performer, Category category, LocalDateTime date,
                   String description, int maxAudience, int price){
        this.title = title;
        this.performer = performer;
        this.category = category;
        status = ConcertStatus.ENDED;
        this.date = date;
        this.description = description;
        this.maxAudience = maxAudience;
        this.price = price;
        likesCount = 0;
    }

    public void updateInfo(String title, LocalDateTime date, String description,
                           int maxAudience, int price){
        this.title = title;
        this.description = description;
        this.date = date;
        // TODO : validate
        this.maxAudience = maxAudience;
        this.price = price;
    }
}
