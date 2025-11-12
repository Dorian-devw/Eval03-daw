package com.sinca.restaurant_management.repository;

import com.sinca.restaurant_management.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}