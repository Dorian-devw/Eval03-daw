package com.sinca.restaurant_management.service;


import com.sinca.restaurant_management.entity.Mesa;
import com.sinca.restaurant_management.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> listar() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> buscarPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa guardar(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa actualizar(Mesa mesa) {
        if (mesa.getId() == null) throw new IllegalArgumentException("La mesa debe tener ID.");
        return mesaRepository.save(mesa);
    }

    public void eliminar(Long id) {
        mesaRepository.deleteById(id);
    }

    public Mesa cambiarEstado(Long id, String nuevoEstado) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada"));
        mesa.setEstado(nuevoEstado);
        return mesaRepository.save(mesa);
    }
}


