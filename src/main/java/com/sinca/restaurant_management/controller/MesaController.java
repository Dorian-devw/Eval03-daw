package com.sinca.restaurant_management.controller;

import com.sinca.restaurant_management.entity.Mesa;
import com.sinca.restaurant_management.service.MesaService;
import com.sinca.restaurant_management.aop.Auditable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mesas", mesaService.listar());
        return "mesa/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesa/form";
    }

    @PostMapping("/guardar")
    @Auditable("Registrar o actualizar mesa")
    public String guardar(@ModelAttribute Mesa mesa) {
        mesaService.guardar(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada"));
        model.addAttribute("mesa", mesa);
        return "mesa/form";
    }

    @GetMapping("/eliminar/{id}")
    @Auditable("Eliminar mesa")
    public String eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return "redirect:/mesas";
    }

    @GetMapping("/estado/{id}/{estado}")
    @Auditable("Cambiar estado de mesa")
    public String cambiarEstado(@PathVariable Long id, @PathVariable String estado) {
        mesaService.cambiarEstado(id, estado);
        return "redirect:/mesas";
    }
}
