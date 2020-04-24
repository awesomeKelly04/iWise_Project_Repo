package com.megadel.iwiseapi.repository;

import com.megadel.iwiseapi.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale, Integer> {
}
