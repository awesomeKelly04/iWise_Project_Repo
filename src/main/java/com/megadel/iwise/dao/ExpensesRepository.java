package com.megadel.iwise.dao;

import com.megadel.iwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {
}
