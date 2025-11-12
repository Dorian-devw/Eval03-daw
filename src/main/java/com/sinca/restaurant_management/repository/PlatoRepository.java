package com.sinca.restaurant_management.repository;

import com.sinca.restaurant_management.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
}