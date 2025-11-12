package com.sinca.restaurant_management.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plato_insumo")
public class PlatoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plato_id")
    private Plato plato;

    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    private BigDecimal cantidadUsada;

    public PlatoInsumo() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) { this.plato = plato; }
    public Insumo getInsumo() { return insumo; }
    public void setInsumo(Insumo insumo) { this.insumo = insumo; }
    public BigDecimal getCantidadUsada() { return cantidadUsada; }
    public void setCantidadUsada(BigDecimal cantidadUsada) { this.cantidadUsada = cantidadUsada; }
}
