package com.megadel.iwiseapi.repository;

import com.megadel.iwiseapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
