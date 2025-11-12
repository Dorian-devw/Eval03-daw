package com.sinca.restaurant_management.repository;

import com.sinca.restaurant_management.entity.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
}