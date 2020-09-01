package com.tzhenia.real.estate.company.service.impl;

import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;
import com.tzhenia.real.estate.company.exception.RealEstateNotFoundException;
import com.tzhenia.real.estate.company.repository.RealEstateRepository;
import com.tzhenia.real.estate.company.service.RealEstateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstate> findAll() {
        log.info("Find all real estates");
        return realEstateRepository.findAll();
    }

    @Override
    public List<RealEstate> findByStatus(Status status) {
        log.info("Find all real estates by status: {}", status);
        return realEstateRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public RealEstate findById(Long id) {
        log.info("Find real estate by id: {}", id);
        realEstateRepository.increaseView(id);

        return realEstateRepository.findById(id)
                .orElseThrow(
                        () -> new RealEstateNotFoundException("Real estate not found by id: " + id)
                );
    }

    @Override
    public void save(RealEstate realEstate) {
        log.info("Save real estate {}", realEstate);
        realEstateRepository.save(realEstate);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete real estate by id: {}", id);
        try {
            realEstateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RealEstateNotFoundException(e.getMessage());
        }
    }

    @Override
    public void updateStatus(Long id, Status status) {
        realEstateRepository.updateStatus(id, status);
        log.info("Apartment by id: {}, status changed to: {}", id, status);
    }
}