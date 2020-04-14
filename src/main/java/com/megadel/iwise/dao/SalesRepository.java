package com.megadel.iwise.dao;

import com.megadel.iwise.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale, Integer> {
}
