package com.sinca.restaurant_management.controller;

import com.sinca.restaurant_management.entity.Plato;
import com.sinca.restaurant_management.service.PlatoService;
import com.sinca.restaurant_management.aop.Auditable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/platos")
public class PlatoController {

    private final PlatoService platoService;

    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("platos", platoService.listar());
        return "plato/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("plato", new Plato());
        return "plato/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Plato plato) {
        platoService.guardar(plato);
        return "redirect:/platos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Plato plato = platoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado"));
        model.addAttribute("plato", plato);
        return "plato/form";
    }

    @GetMapping("/eliminar/{id}")
    @Auditable("Eliminar plato")
    public String eliminar(@PathVariable Long id) {
        platoService.eliminar(id);
        return "redirect:/platos";
    }
}
