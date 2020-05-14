package com.megadel.repository;

import com.megadel.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {
}
