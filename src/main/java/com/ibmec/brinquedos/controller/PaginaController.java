package com.ibmec.brinquedos.controller;

import com.ibmec.brinquedos.service.BrinquedoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    private final BrinquedoService service;

    public PaginaController(BrinquedoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/painel")
    public String painel(Authentication auth, Model model) {
        boolean admin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("usuario", auth.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("brinquedos", service.listarTodos());
        return "painel";
    }
}