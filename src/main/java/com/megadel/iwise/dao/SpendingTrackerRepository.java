package com.megadel.iwise.dao;

import com.megadel.iwise.entity.SpendingTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="spendings")
public interface SpendingTrackerRepository extends JpaRepository<SpendingTracker, Integer> {
}
