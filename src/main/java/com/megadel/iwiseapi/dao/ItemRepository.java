package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
