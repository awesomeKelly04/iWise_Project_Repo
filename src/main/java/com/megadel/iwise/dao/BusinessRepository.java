package com.megadel.iwise.dao;

import com.megadel.iwise.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
}
