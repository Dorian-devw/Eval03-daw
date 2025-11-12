package com.sinca.restaurant_management.service;

import com.sinca.restaurant_management.entity.Plato;
import com.sinca.restaurant_management.repository.PlatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {

    private final PlatoRepository platoRepository;

    public PlatoService(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    public List<Plato> listar() {
        return platoRepository.findAll();
    }

    public Optional<Plato> buscarPorId(Long id) {
        return platoRepository.findById(id);
    }

    public Plato guardar(Plato plato) {
        return platoRepository.save(plato);
    }

    public Plato actualizar(Plato plato) {
        if (plato.getId() == null)
            throw new IllegalArgumentException("El plato debe tener ID para actualizar.");
        return platoRepository.save(plato);
    }

    public void eliminar(Long id) {
        platoRepository.deleteById(id);
    }
}
