package com.sinca.restaurant_management.controller;

import com.sinca.restaurant_management.entity.*;
import com.sinca.restaurant_management.service.*;
import com.sinca.restaurant_management.aop.Auditable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final MesaService mesaService;
    private final PlatoService platoService;

    public PedidoController(PedidoService pedidoService,
                            ClienteService clienteService,
                            MesaService mesaService,
                            PlatoService platoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.mesaService = mesaService;
        this.platoService = platoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedido/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("mesas", mesaService.listar());
        model.addAttribute("platos", platoService.listar());
        return "pedido/form";
    }

    @PostMapping("/guardar")
    @Auditable("Registrar pedido")
    public String guardar(@ModelAttribute Pedido pedido) {
        pedidoService.guardar(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
        model.addAttribute("pedido", pedido);
        return "pedido/ver";
    }

    @GetMapping("/cambiarEstado/{id}/{estado}")
    @Auditable("Cambiar estado del pedido")
    public String cambiarEstado(@PathVariable Long id, @PathVariable String estado) {
        pedidoService.cambiarEstado(id, estado);
        return "redirect:/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    @Auditable("Eliminar pedido")
    public String eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }
}

