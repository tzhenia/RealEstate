package com.tzhenia.real.estate.company.web.controller;

import com.tzhenia.real.estate.company.ServerRoute;
import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;
import com.tzhenia.real.estate.company.exception.RealEstateNotFoundException;
import com.tzhenia.real.estate.company.service.RealEstateService;
import com.tzhenia.real.estate.company.utils.MessageUtil;
import com.tzhenia.real.estate.company.web.interceptors.GlobalControllerExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.tzhenia.real.estate.company.JsonMessage.REAL_ESTATE_SAVE;
import static com.tzhenia.real.estate.company.JsonMessage.REAL_ESTATE_UPDATE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RealEstateControllerTest {
    @Mock
    private RealEstateService realEstateService;

    @InjectMocks
    private RealEstateController realEstateController;

    private MockMvc sut;

    @BeforeEach
    public void setUp() {
        sut = MockMvcBuilders
                .standaloneSetup(realEstateController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void shouldReturnRealEstateList() throws Exception {
        //when
        when(realEstateService.findAll()).thenReturn(new ArrayList<>());

        sut.perform(
                get(ServerRoute.REAL_ESTATE_API.getRoute())
        ).andExpect(status().isOk());

        //then
        verify(realEstateService).findAll();
    }

    @Test
    public void shouldReturnRealEstateListByStatus() throws Exception {
        //when
        when(realEstateService.findByStatus(Status.SOLD)).thenReturn(new ArrayList<>());

        sut.perform(
                get(ServerRoute.REAL_ESTATE_SEARCH_BY_STATUS_API.getRoute(), "SOLD")
        ).andExpect(status().isOk());

        //then
        verify(realEstateService).findByStatus(Status.SOLD);
    }

    @Test
    public void shouldReturnRealEstate() throws Exception {
        //when
        when(realEstateService.findById(1L)).thenReturn(new RealEstate());

        sut.perform(
                get(ServerRoute.REAL_ESTATE_ID_API.getRoute(), "1")
        ).andExpect(status().isOk());

        //then
        verify(realEstateService).findById(1L);
    }

    @Test
    public void shouldThrowRealEstateNotFoundException() throws Exception {
        //when
        when(realEstateService.findById(0L)).thenThrow(new RealEstateNotFoundException("TEST"));

        sut.perform(
                get(ServerRoute.REAL_ESTATE_ID_API.getRoute(), "0")
        ).andExpect(status().isNotFound());

        //then
        verify(realEstateService).findById(0L);
    }

    @Test
    public void shouldSaveRealEstate() throws Exception {
        // given
        RealEstate realEstate = initRealEstate();
        realEstate.setId(null);

        final String message = MessageUtil.getMessage(REAL_ESTATE_SAVE.getJsonName());

        //when
        sut.perform(
                post(ServerRoute.REAL_ESTATE_API.getRoute())
                        .content(message)
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());

        //then
        verify(realEstateService).save(realEstate);
    }

    @Test
    public void shouldUpdateRealEstate() throws Exception {
        // given
        RealEstate realEstate = initRealEstate();
        final String message = MessageUtil.getMessage(REAL_ESTATE_UPDATE.getJsonName());

        //when
        sut.perform(
                put(ServerRoute.REAL_ESTATE_API.getRoute())
                        .content(message)
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());

        //then
        verify(realEstateService).save(realEstate);
    }

    @Test
    public void shouldRemoveRealEstate() throws Exception {
        sut.perform(
                delete(ServerRoute.REAL_ESTATE_ID_API.getRoute(), 0)
        ).andExpect(status().isOk());

        //then
        verify(realEstateService).deleteById(0L);
    }

    private RealEstate initRealEstate() {
        return new RealEstate(
                1L,
                "White House",
                new BigDecimal(500000),
                "Pine street 32",
                Status.FOR_SALE,
                new Agent(1L, null, null),
                99L
        );
    }
}