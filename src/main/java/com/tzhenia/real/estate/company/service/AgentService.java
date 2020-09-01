package com.tzhenia.real.estate.company.service;

import com.tzhenia.real.estate.company.entity.Agent;

import java.util.List;

public interface AgentService {
    List<Agent> findAll();

    Agent findById(Long id);

    void save(Agent agent);

    void deleteById(Long id);
}