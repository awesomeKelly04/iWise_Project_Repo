package com.megadel.iwise.dao;

import com.megadel.iwise.entity.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Integer> {
}
