package com.tzhenia.real.estate.company.web.controller;

import com.tzhenia.real.estate.company.ServerRoute;
import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.exception.AgentNotFoundException;
import com.tzhenia.real.estate.company.service.AgentService;
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

import java.util.ArrayList;

import static com.tzhenia.real.estate.company.JsonMessage.AGENT_SAVE;
import static com.tzhenia.real.estate.company.JsonMessage.AGENT_UPDATE;
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
public class AgentControllerTest {
    @Mock
    private AgentService agentService;

    @InjectMocks
    private AgentController agentController;

    private MockMvc sut;

    @BeforeEach
    public void setUp() {
        sut = MockMvcBuilders
                .standaloneSetup(agentController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void shouldReturnAgentList() throws Exception {
        //when
        when(agentService.findAll()).thenReturn(new ArrayList<>());

        sut.perform(
                get(ServerRoute.AGENT_API.getRoute())
        ).andExpect(status().isOk());

        //then
        verify(agentService).findAll();
    }

    @Test
    public void shouldReturnAgent() throws Exception {
        //when
        when(agentService.findById(1L)).thenReturn(new Agent());

        sut.perform(
                get(ServerRoute.AGENT_ID_API.getRoute(), "1")
        ).andExpect(status().isOk());

        //then
        verify(agentService).findById(1L);
    }

    @Test
    public void shouldThrowAgentNotFoundException() throws Exception {
        //when
        when(agentService.findById(0L)).thenThrow(new AgentNotFoundException("TEST"));

        sut.perform(
                get(ServerRoute.AGENT_ID_API.getRoute(), "0")
        ).andExpect(status().isNotFound());

        //then
        verify(agentService).findById(0L);
    }

    @Test
    public void shouldSaveAgent() throws Exception {
        // given
        Agent agent = new Agent(null, "Yevhenii", "Taranukha");
        final String message = MessageUtil.getMessage(AGENT_SAVE.getJsonName());

        //when
        sut.perform(
                post(ServerRoute.AGENT_API.getRoute())
                        .content(message)
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());

        //then
        verify(agentService).save(agent);
    }

    @Test
    public void shouldUpdateAgent() throws Exception {
        // given
        Agent agent = new Agent(1L, "Yevhenii", "Taranukha");
        final String message = MessageUtil.getMessage(AGENT_UPDATE.getJsonName());

        //when
        sut.perform(
                put(ServerRoute.AGENT_API.getRoute())
                        .content(message)
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());

        //then
        verify(agentService).save(agent);
    }

    @Test
    public void shouldRemoveAgent() throws Exception {
        sut.perform(
                delete(ServerRoute.AGENT_ID_API.getRoute(), 0)
        ).andExpect(status().isOk());

        //then
        verify(agentService).deleteById(0L);
    }
}