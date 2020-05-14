package com.megadel.repository;

import com.megadel.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
}
