package com.megadel.iwiseapi.dao;

import com.megadel.iwiseapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
