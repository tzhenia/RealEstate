package com.tzhenia.real.estate.company.repository;

import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    @Modifying
    @Query("UPDATE RealEstate SET views = views + 1 WHERE id = :id")
    void increaseView(@Param("id") Long id);

    @Modifying
    @Query("UPDATE RealEstate SET status = :status WHERE id = :id")
    void updateStatus(@Param("id") Long id, Status status);

    List<RealEstate> findByStatus(Status status);
}