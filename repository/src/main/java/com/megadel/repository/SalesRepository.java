package com.megadel.repository;

import com.megadel.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale, Integer> {
}
