package com.example.RuleEngine.repository;

import com.example.RuleEngine.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    // You can add custom queries if needed
}