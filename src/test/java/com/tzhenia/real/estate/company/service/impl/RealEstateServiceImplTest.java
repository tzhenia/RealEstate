package com.tzhenia.real.estate.company.service.impl;

import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;
import com.tzhenia.real.estate.company.exception.RealEstateNotFoundException;
import com.tzhenia.real.estate.company.repository.RealEstateRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RealEstateServiceImplTest {
    @Mock
    private RealEstateRepository realEstateRepository;

    @Captor
    private ArgumentCaptor<RealEstate> argumentCaptor;

    @InjectMocks
    private RealEstateServiceImpl sut;

    @Test
    void shouldLoadRealEstateList() {
        //given
        List<RealEstate> expectedList = initRealEstateList();

        //when
        when(realEstateRepository.findAll()).thenReturn(expectedList);

        List<RealEstate> actualList = sut.findAll();

        //then
        assertThat(actualList).isEqualTo(expectedList);
        verify(realEstateRepository).findAll();
    }

    @Test
    void shouldLoadRealEstateListByStatus() {
        //given
        Status status = Status.FOR_SALE;
        List<RealEstate> expectedList = initRealEstateList();

        //when
        when(realEstateRepository.findByStatus(status)).thenReturn(expectedList);

        List<RealEstate> actualList = sut.findByStatus(status);

        //then
        assertThat(actualList).isEqualTo(expectedList);
        verify(realEstateRepository).findByStatus(status);
    }

    @Test
    void shouldLoadRealEstate() {
        //given
        final Long id = 1L;
        RealEstate expected = initRealEstate();

        //when
        when(realEstateRepository.findById(id)).thenReturn(Optional.of(expected));

        RealEstate actual = sut.findById(id);

        //then
        assertThat(actual).isEqualTo(expected);
        verify(realEstateRepository).findById(id);
    }

    @Test
    void shouldThrowRealEstateNotFoundException() {
        //given
        final Long id = 0L;

        //when_then
        assertThrows(RealEstateNotFoundException.class, () -> sut.findById(id));
        verify(realEstateRepository).findById(id);
    }

    @Test
    void shouldSaveRealEstate() {
        //given
        RealEstate realEstate = initRealEstate();
        realEstate.setId(null);

        sut.save(realEstate);

        //then
        verify(realEstateRepository).save(argumentCaptor.capture());
        List<RealEstate> captorValues = argumentCaptor.getAllValues();
        assertThat(realEstate).isEqualTo(captorValues.get(0));
    }

    @Test
    void shouldUpdateRealEstate() {
        //given
        RealEstate realEstate = initRealEstate();

        sut.save(realEstate);

        //then
        verify(realEstateRepository).save(argumentCaptor.capture());
        List<RealEstate> captorValues = argumentCaptor.getAllValues();
        assertThat(realEstate).isEqualTo(captorValues.get(0));
    }

    @Test
    void shouldDeleteRealEstate() {
        //given
        final Long id = 0L;

        sut.deleteById(id);

        //then
        verify(realEstateRepository).deleteById(id);
    }

    private static List<RealEstate> initRealEstateList() {
        return Stream.of(
                initRealEstate()
        ).collect(Collectors.toList());
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