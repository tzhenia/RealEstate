package com.tzhenia.real.estate.company.repository;

import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByAgentId(Long id);

    @Query(value = "SELECT agent_id as agentId, sum(price) as sum from deal group by agent_id limit :limit", nativeQuery = true)
    List<Statistic> findTopSellers(int limit);
}
