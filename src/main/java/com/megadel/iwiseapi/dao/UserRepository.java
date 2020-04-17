package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
