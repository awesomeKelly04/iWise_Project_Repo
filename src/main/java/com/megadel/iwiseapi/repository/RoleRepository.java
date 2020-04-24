package com.megadel.iwiseapi.repository;

import com.megadel.iwiseapi.entity.Role;
import com.megadel.iwiseapi.projectenum.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
