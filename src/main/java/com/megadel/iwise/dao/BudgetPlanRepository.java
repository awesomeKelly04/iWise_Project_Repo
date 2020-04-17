package com.megadel.iwise.dao;

import com.megadel.iwise.entity.BudgetPlan;
import org.dom4j.XPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="budgets")
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Integer> {
}
