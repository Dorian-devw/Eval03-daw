package com.sinca.restaurant_management.repository;

import com.sinca.restaurant_management.entity.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
}