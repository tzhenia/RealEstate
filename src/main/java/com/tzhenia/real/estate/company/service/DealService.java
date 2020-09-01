package com.tzhenia.real.estate.company.service;

import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.model.Statistic;

import java.util.List;

public interface DealService {
    List<Deal> findAll();

    List<Deal> findByAgentId(Long id);

    List<Statistic> findTopSellers(int limit);

    Deal findById(Long id);

    void save(Deal deal);
}
