package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
