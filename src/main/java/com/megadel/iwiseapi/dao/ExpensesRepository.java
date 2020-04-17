package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {
}
