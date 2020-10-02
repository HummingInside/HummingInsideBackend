package com.backend.core.concert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Query("select c from Concert c where c.status= :status " +
            "and c.category.id= :categoryId and c.performer.name like :name")
    List<Concert> findAllByCriteria(@Param("status") String status, Long categoryId, String name);
}
