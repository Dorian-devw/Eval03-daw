package com.sinca.restaurant_management.repository;

import com.sinca.restaurant_management.entity.PlatoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoInsumoRepository extends JpaRepository<PlatoInsumo, Long> {


    List<PlatoInsumo> findByPlatoId(Long id);
}
