package com.backend.core.concert;

import com.backend.core.BaseTimeEntity;
import com.backend.core.member.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
public class Concert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "performer_id")
    private Member performer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ConcertStatus status;

    private LocalDateTime date;
    private String description;
    private int maxAudience;

    @ColumnDefault("0")
    private int currentAudience;
    private int price;

    @ColumnDefault("0")
    private int likesCount;
    private String imgUrl;

    @Builder
    public Concert(String title, Member performer, Category category, LocalDateTime date,
                   String description, int maxAudience, int price, String imgUrl){
        updateInfo(title, date, description, maxAudience, 0, price, imgUrl);
        this.performer = performer;
        this.category = category;
        status = ConcertStatus.UPCOMING;
        likesCount = 0;
    }

    public void updateInfo(String title, LocalDateTime date, String description,
                           int maxAudience, int currentAudience, int price, String imgUrl){
        this.title = title;
        this.description = description;
        this.date = date;
        // TODO : validate
        this.maxAudience = maxAudience;
        this.currentAudience = currentAudience;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
