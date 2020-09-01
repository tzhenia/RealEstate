package com.tzhenia.real.estate.company.web.controller;

import com.tzhenia.real.estate.company.ServerRoute;
import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.exception.DealNotFoundException;
import com.tzhenia.real.estate.company.facade.DealFacade;
import com.tzhenia.real.estate.company.service.DealService;
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

import static com.tzhenia.real.estate.company.JsonMessage.DEAL_SAVE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class DealControllerTest {
    @Mock
    private DealService dealService;

    @Mock
    private DealFacade dealFacade;

    @InjectMocks
    private DealController dealController;

    private MockMvc sut;

    @BeforeEach
    public void setUp() {
        sut = MockMvcBuilders
                .standaloneSetup(dealController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void shouldReturnDealList() throws Exception {
        //when
        when(dealService.findAll()).thenReturn(new ArrayList<>());

        sut.perform(
                get(ServerRoute.DEAL_API.getRoute())
        ).andExpect(status().isOk());

        //then
        verify(dealService).findAll();
    }

    @Test
    public void shouldReturnDealListByAgent() throws Exception {
        //when
        when(dealService.findByAgentId(1L)).thenReturn(new ArrayList<>());

        sut.perform(
                get(ServerRoute.DEAL_BY_AGENT_API.getRoute(), "1")
        ).andExpect(status().isOk());

        //then
        verify(dealService).findByAgentId(1L);
    }

    @Test
    public void shouldReturnDeal() throws Exception {
        //when
        when(dealService.findById(1L)).thenReturn(new Deal());

        sut.perform(
                get(ServerRoute.DEAL_ID_API.getRoute(), "1")
        ).andExpect(status().isOk());

        //then
        verify(dealService).findById(1L);
    }

    @Test
    public void shouldReturnTopSellers() throws Exception {
        //when
        when(dealService.findTopSellers(2)).thenReturn(new ArrayList<>());

        sut.perform(
                get(ServerRoute.DEAL_BY_TOP_SELLERS_API.getRoute(), "2")
        ).andExpect(status().isOk());

        //then
        verify(dealService).findTopSellers(2);
    }

    @Test
    public void shouldThrowDealNotFoundException() throws Exception {
        //when
        when(dealService.findById(0L)).thenThrow(new DealNotFoundException("TEST"));

        sut.perform(
                get(ServerRoute.DEAL_ID_API.getRoute(), "0")
        ).andExpect(status().isNotFound());

        //then
        verify(dealService).findById(0L);
    }

    @Test
    public void shouldSaveDeal() throws Exception {
        // given
        Deal deal = initDeal();
        final String message = MessageUtil.getMessage(DEAL_SAVE.getJsonName());

        //when
        sut.perform(
                post(ServerRoute.DEAL_API.getRoute())
                        .content(message)
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());

        //then
        verify(dealFacade).sellRealEstate(deal);
    }

    private static Deal initDeal() {
        return new Deal(
                null,
                new RealEstate(1L, null, null, null, null, null, null),
                new Agent(1L, null, null),
                new BigDecimal(1000000)
        );
    }
}