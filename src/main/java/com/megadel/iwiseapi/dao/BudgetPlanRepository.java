package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="budgets")
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Integer> {
}
