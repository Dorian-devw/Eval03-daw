package com.sinca.restaurant_management.controller;

import com.sinca.restaurant_management.entity.Insumo;
import com.sinca.restaurant_management.service.InsumoService;
import com.sinca.restaurant_management.aop.Auditable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insumos")
public class InsumoController {

    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("insumos", insumoService.listar());
        return "insumo/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("insumo", new Insumo());
        return "insumo/form";
    }

    @PostMapping("/guardar")
    @Auditable("Registrar o actualizar insumo")
    public String guardar(@ModelAttribute Insumo insumo) {
        insumoService.guardar(insumo);
        return "redirect:/insumos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Insumo insumo = insumoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Insumo no encontrado"));
        model.addAttribute("insumo", insumo);
        return "insumo/form";
    }

    @GetMapping("/eliminar/{id}")
    @Auditable("Eliminar insumo")
    public String eliminar(@PathVariable Long id) {
        insumoService.eliminar(id);
        return "redirect:/insumos";
    }
}
