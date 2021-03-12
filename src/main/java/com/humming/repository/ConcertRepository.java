package com.humming.repository;

import com.humming.api.dto.concert.ConcertSimpleResponse;
import com.humming.domain.Concert;
import com.humming.domain.ConcertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Query("select c from Concert c where (:status is null or c.status= :status) " +
            "and (:categoryId is null or c.category.id= :categoryId) " +
            "and (:performerName is null or c.performer.name like :performerName) order by c.id desc ")
    List<Concert> findAllByCriteria(@Param("status") ConcertStatus status,
                                    @Param("categoryId") Long categoryId,
                                    @Param("performerName") String performerName);

    List<ConcertSimpleResponse> findAllByPerformer_Id(Long PerformerId);
}
