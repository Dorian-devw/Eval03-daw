package com.sinca.restaurant_management.service;

import com.sinca.restaurant_management.entity.*;
import com.sinca.restaurant_management.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final PlatoInsumoRepository platoInsumoRepository;
    private final InsumoRepository insumoRepository;
    private final MesaRepository mesaRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         DetallePedidoRepository detallePedidoRepository,
                         PlatoInsumoRepository platoInsumoRepository,
                         InsumoRepository insumoRepository,
                         MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.platoInsumoRepository = platoInsumoRepository;
        this.insumoRepository = insumoRepository;
        this.mesaRepository = mesaRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido guardar(Pedido pedido) {
        // Guardar cabecera
        Pedido p = pedidoRepository.save(pedido);

        // Guardar detalles
        if (p.getDetalles() != null) {
            for (DetallePedido det : p.getDetalles()) {
                det.setPedido(p);
                BigDecimal precio = det.getPlato().getPrecio() != null ? det.getPlato().getPrecio() : BigDecimal.ZERO;
                det.setSubtotal(precio.multiply(BigDecimal.valueOf(det.getCantidad())));
                detallePedidoRepository.save(det);
            }
        }

        // Marcar mesa como "ocupada"
        if (p.getMesa() != null) {
            mesaRepository.findById(p.getMesa().getId()).ifPresent(m -> {
                m.setEstado("ocupada");
                mesaRepository.save(m);
            });
        }

        // Descontar insumos
        if (p.getDetalles() != null) {
            for (DetallePedido det : p.getDetalles()) {
                List<PlatoInsumo> relaciones = platoInsumoRepository.findByPlatoId(det.getPlato().getId());
                for (PlatoInsumo pi : relaciones) {
                    Insumo insumo = pi.getInsumo();
                    BigDecimal cantidadUsada = pi.getCantidadUsada()
                            .multiply(BigDecimal.valueOf(det.getCantidad()));
                    insumo.setStock(insumo.getStock().subtract(cantidadUsada));
                    insumoRepository.save(insumo);
                }
            }
        }
        return p;
    }

    @Transactional
    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
        pedido.setEstado(nuevoEstado);

        // Si se cierra el pedido, liberar mesa
        if ("cerrado".equalsIgnoreCase(nuevoEstado) && pedido.getMesa() != null) {
            Mesa mesa = pedido.getMesa();
            mesa.setEstado("disponible");
            mesaRepository.save(mesa);
        }

        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
}

