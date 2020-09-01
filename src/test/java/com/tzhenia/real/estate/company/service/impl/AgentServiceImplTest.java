package com.tzhenia.real.estate.company.service.impl;

import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.exception.AgentNotFoundException;
import com.tzhenia.real.estate.company.repository.AgentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgentServiceImplTest {
    @Mock
    private AgentRepository agentRepository;

    @Captor
    private ArgumentCaptor<Agent> argumentCaptor;

    @InjectMocks
    private AgentServiceImpl sut;

    @Test
    void shouldLoadAgentList() {
        //given
        List<Agent> expectedList = initAgentList();

        //when
        when(agentRepository.findAll()).thenReturn(expectedList);

        List<Agent> actualList = sut.findAll();

        //then
        assertThat(actualList).isEqualTo(expectedList);
        verify(agentRepository).findAll();
    }

    @Test
    void shouldLoadAgent() {
        //given
        final Long id = 1L;
        Agent expected = new Agent(1L, "Yevhenii", "Taranukha");

        //when
        when(agentRepository.findById(id)).thenReturn(Optional.of(expected));

        Agent actual = sut.findById(id);

        //then
        assertThat(actual).isEqualTo(expected);
        verify(agentRepository).findById(id);
    }

    @Test
    void shouldThrowAgentNotFoundException() {
        //given
        final Long id = 0L;

        //when_then
        assertThrows(AgentNotFoundException.class, () -> sut.findById(id));
        verify(agentRepository).findById(id);
    }

    @Test
    void shouldSaveAgent() {
        //given
        Agent agent = new Agent(null, "Yevhenii", "Taranukha");

        sut.save(agent);

        //then
        verify(agentRepository).save(argumentCaptor.capture());
        List<Agent> captorValues = argumentCaptor.getAllValues();
        assertThat(agent).isEqualTo(captorValues.get(0));
    }

    @Test
    void shouldUpdateAgent() {
        //given
        Agent agent = new Agent(1L, "Yevhenii", "Taranukha");

        sut.save(agent);

        //then
        verify(agentRepository).save(argumentCaptor.capture());
        List<Agent> captorValues = argumentCaptor.getAllValues();
        assertThat(agent).isEqualTo(captorValues.get(0));
    }

    @Test
    void shouldDeleteAgent() {
        //given
        final Long id = 0L;

        sut.deleteById(id);

        //then
        verify(agentRepository).deleteById(id);
    }

    private static List<Agent> initAgentList() {
        return Stream.of(
                new Agent(1L, "Yevhenii", "Taranukha"),
                new Agent(2L, "Jon", "Snow"),
                new Agent(3L, "Daenerys", "Targaryen")
        ).collect(Collectors.toList());
    }
}