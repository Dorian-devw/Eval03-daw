package com.sinca.restaurant_management.repository;

import com.sinca.restaurant_management.entity.Cliente;
import com.sinca.restaurant_management.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    @Query("SELECT m FROM Mesa m WHERE m.estado = 'disponible'")
    List<Mesa> findDisponibles();
}