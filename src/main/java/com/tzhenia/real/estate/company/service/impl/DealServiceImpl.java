package com.tzhenia.real.estate.company.service.impl;

import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.exception.DealNotFoundException;
import com.tzhenia.real.estate.company.model.Statistic;
import com.tzhenia.real.estate.company.repository.DealRepository;
import com.tzhenia.real.estate.company.service.DealService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    @Override
    public List<Deal> findAll() {
        log.info("Find all deals");
        return dealRepository.findAll();
    }

    @Override
    public List<Deal> findByAgentId(Long id) {
        log.info("Find all deals by agent id: {}", id);
        return dealRepository.findByAgentId(id);
    }

    @Override
    public List<Statistic> findTopSellers(int limit) {
        log.info("Find find top sellers by limit: {}", limit);
        return dealRepository.findTopSellers(limit);
    }

    @Override
    public Deal findById(Long id) {
        log.info("Find deal by id: {}", id);

        return dealRepository.findById(id)
                .orElseThrow(
                        () -> new DealNotFoundException("Deal not found by id: " + id)
                );
    }

    @Override
    public void save(Deal deal) {
        log.info("Save deal {}", deal);
        dealRepository.save(deal);
    }
}