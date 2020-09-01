package com.tzhenia.real.estate.company.repository;

import com.tzhenia.real.estate.company.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
