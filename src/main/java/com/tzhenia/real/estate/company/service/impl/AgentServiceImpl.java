package com.tzhenia.real.estate.company.service.impl;

import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.exception.AgentNotFoundException;
import com.tzhenia.real.estate.company.repository.AgentRepository;
import com.tzhenia.real.estate.company.service.AgentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    @Override
    public List<Agent> findAll() {
        log.info("Find all agents");
        return agentRepository.findAll();
    }

    @Override
    public Agent findById(Long id) {
        log.info("Find agent by id: {}", id);

        return agentRepository.findById(id)
                .orElseThrow(
                        () -> new AgentNotFoundException("Agent not found by id: " + id)
                );
    }

    @Override
    public void save(Agent agent) {
        log.info("Save agent {}", agent);
        agentRepository.save(agent);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete agent by id: {}", id);
        try {
            agentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AgentNotFoundException(e.getMessage());
        }
    }
}