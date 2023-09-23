package com.angularspringbootecommerce.backend.repository;

import com.angularspringbootecommerce.backend.models.ModRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModRoleRepository extends JpaRepository<ModRole, Long> {
    Optional<ModRole> findByAuthority(String authority);
}