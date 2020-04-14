package com.megadel.iwise.dao;

import com.megadel.iwise.entity.SpendingTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingTrackerRepository extends JpaRepository<SpendingTracker, Integer> {
}
