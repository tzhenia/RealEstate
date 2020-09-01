package com.tzhenia.real.estate.company.service;

import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;

import java.util.List;

public interface RealEstateService {
    List<RealEstate> findAll();

    List<RealEstate> findByStatus(Status status);

    RealEstate findById(Long id);

    void save(RealEstate realEstate);

    void deleteById(Long id);

    void updateStatus(Long id, Status status);
}