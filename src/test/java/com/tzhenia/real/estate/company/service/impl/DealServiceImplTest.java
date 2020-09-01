package com.tzhenia.real.estate.company.service.impl;

import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.exception.DealNotFoundException;
import com.tzhenia.real.estate.company.repository.DealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;

    @Captor
    private ArgumentCaptor<Deal> argumentCaptor;

    @InjectMocks
    private DealServiceImpl sut;

    @Test
    void shouldLoadDealList() {
        //given
        List<Deal> expectedList = initDealList();

        //when
        when(dealRepository.findAll()).thenReturn(expectedList);

        List<Deal> actualList = sut.findAll();

        //then
        assertThat(actualList).isEqualTo(expectedList);
        verify(dealRepository).findAll();
    }

    @Test
    void shouldLoadDealListByAgentId() {
        //given
        final Long id = 1L;
        List<Deal> expectedList = initDealList();

        //when
        when(dealRepository.findByAgentId(id)).thenReturn(expectedList);

        List<Deal> actualList = sut.findByAgentId(id);

        //then
        assertThat(actualList).isEqualTo(expectedList);
        assertThat(actualList.get(0).getAgent().getId()).isEqualTo(id);
        verify(dealRepository).findByAgentId(id);
    }

    @Test
    void shouldLoadTopSellers() {
        //given
        final int id = 1;
        List<Deal> expectedList = initDealList();

        //when
        when(dealRepository.findTopSellers(id)).thenReturn(any());

        sut.findTopSellers(id);

        //then
        verify(dealRepository).findTopSellers(id);
    }

    @Test
    void shouldLoadDeal() {
        //given
        final Long id = 1L;
        Deal expected = initDeal();

        //when
        when(dealRepository.findById(id)).thenReturn(Optional.of(expected));

        Deal actual = sut.findById(id);

        //then
        assertThat(actual).isEqualTo(expected);
        verify(dealRepository).findById(id);
    }

    @Test
    void shouldThrowDealNotFoundException() {
        //given
        final Long id = 0L;

        //when_then
        assertThrows(DealNotFoundException.class, () -> sut.findById(id));
        verify(dealRepository).findById(id);
    }

    @Test
    void shouldSaveDeal() {
        //given
        Deal deal = initDeal();
        deal.setId(null);

        sut.save(deal);

        //then
        verify(dealRepository).save(argumentCaptor.capture());
        List<Deal> captorValues = argumentCaptor.getAllValues();
        assertThat(deal).isEqualTo(captorValues.get(0));
    }


    private static List<Deal> initDealList() {
        return Stream.of(
                initDeal()
        ).collect(Collectors.toList());
    }

    private static Deal initDeal() {
        return new Deal(
                1L,
                new RealEstate(),
                new Agent(1L, "Yevhenii", "Taranukha"),
                new BigDecimal(100000)
        );
    }
}