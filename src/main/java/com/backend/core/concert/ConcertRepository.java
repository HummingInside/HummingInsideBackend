package com.backend.core.concert;

import com.backend.application.dto.concert.ConcertSimpleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Query("select c from Concert c where (:status is null or c.status= :status) " +
            "and (:categoryId is null or c.category.id= :categoryId) " +
            "and (:performerName is null or c.performer.name like :performerName)")
    List<Concert> findAllByCriteria(@Param("status") ConcertStatus status,
                                    @Param("categoryId") Long categoryId,
                                    @Param("performerName") String performerName);

    List<ConcertSimpleResponse> findAllByPerformer_Id(Long PerformerId);
}
