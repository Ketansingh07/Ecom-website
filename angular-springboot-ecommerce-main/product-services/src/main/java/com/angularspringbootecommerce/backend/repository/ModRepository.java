package com.angularspringbootecommerce.backend.repository;

import com.angularspringbootecommerce.backend.models.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModRepository extends JpaRepository<Mod, Long> {
    Optional<Mod> findByEmail(String email);
}
