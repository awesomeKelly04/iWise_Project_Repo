package com.megadel.repository;

import com.megadel.models.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="budgets")
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Integer> {

}
