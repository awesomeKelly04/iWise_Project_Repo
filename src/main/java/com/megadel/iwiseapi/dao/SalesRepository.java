package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale, Integer> {
}
