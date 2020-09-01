package com.tzhenia.real.estate.company.facade.impl;

import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;
import com.tzhenia.real.estate.company.service.DealService;
import com.tzhenia.real.estate.company.service.RealEstateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DealFacadeImplTest {
    @Mock
    private DealService dealService;

    @Mock
    private RealEstateService realEstateService;

    @InjectMocks
    private DealFacadeImpl sut;

    @Test
    void shouldSaveDeal() {
        //given
        Deal deal = initDeal();

        sut.sellRealEstate(deal);

        //then
        verify(dealService).save(deal);
        verify(realEstateService).updateStatus(deal.getRealEstate().getId(), Status.SOLD);
    }

    private static Deal initDeal() {
        return new Deal(
                null,
                initRealEstate(),
                new Agent(1L, "Yevhenii", "Taranukha"),
                new BigDecimal(540365)
        );
    }

    private static RealEstate initRealEstate() {
        return new RealEstate(
                1L,
                "Rose Cottage",
                new BigDecimal(540365),
                "Wonderful street 17",
                Status.FOR_SALE,
                new Agent(),
                0L
        );
    }
}