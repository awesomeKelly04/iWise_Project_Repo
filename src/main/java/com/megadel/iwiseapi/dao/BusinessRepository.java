package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
}
