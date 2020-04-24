package com.megadel.iwiseapi.repository;

import com.megadel.iwiseapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
