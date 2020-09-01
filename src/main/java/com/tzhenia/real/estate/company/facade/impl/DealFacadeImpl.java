package com.tzhenia.real.estate.company.facade.impl;

import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.entity.Status;
import com.tzhenia.real.estate.company.facade.DealFacade;
import com.tzhenia.real.estate.company.service.DealService;
import com.tzhenia.real.estate.company.service.RealEstateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class DealFacadeImpl implements DealFacade {
    private final DealService dealService;
    private final RealEstateService realEstateService;

    @Override
    @Transactional
    public void sellRealEstate(Deal deal) {
        Long realEstateId = deal.getRealEstate().getId();
        dealService.save(deal);
        realEstateService.updateStatus(realEstateId, Status.SOLD);
    }
}