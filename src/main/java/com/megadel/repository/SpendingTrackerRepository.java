package com.megadel.repository;

import com.megadel.models.SpendingTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="spendings")
public interface SpendingTrackerRepository extends JpaRepository<SpendingTracker, Integer> {
}
