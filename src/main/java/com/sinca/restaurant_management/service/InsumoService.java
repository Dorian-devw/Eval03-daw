package com.sinca.restaurant_management.service;

import com.sinca.restaurant_management.entity.Insumo;
import com.sinca.restaurant_management.repository.InsumoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public List<Insumo> listar() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> buscarPorId(Long id) {
        return insumoRepository.findById(id);
    }

    public Insumo guardar(Insumo insumo) {
        if (insumo.getStock() == null) insumo.setStock(BigDecimal.ZERO);
        if (insumo.getStockMinimo() == null) insumo.setStockMinimo(BigDecimal.ZERO);
        if (insumo.getPrecioCompra() == null) insumo.setPrecioCompra(BigDecimal.ZERO);
        return insumoRepository.save(insumo);
    }

    public Insumo actualizar(Insumo insumo) {
        if (insumo.getId() == null) throw new IllegalArgumentException("El insumo debe tener ID.");
        return insumoRepository.save(insumo);
    }

    public void eliminar(Long id) {
        insumoRepository.deleteById(id);
    }

    public void ajustarStock(Long idInsumo, BigDecimal cantidad) {
        Insumo insumo = insumoRepository.findById(idInsumo)
                .orElseThrow(() -> new IllegalArgumentException("Insumo no encontrado"));
        insumo.setStock(insumo.getStock().add(cantidad));
        insumoRepository.save(insumo);
    }
}
