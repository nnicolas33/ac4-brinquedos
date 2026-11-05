package com.ibmec.brinquedos.controller;

import com.ibmec.brinquedos.model.Brinquedo;
import com.ibmec.brinquedos.service.BrinquedoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    private final BrinquedoService service;

    public BrinquedoController(BrinquedoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Brinquedo> listar(@RequestParam(required = false) String tipo,
                                  @RequestParam(required = false) Integer idadeMaxima) {
        if (tipo != null) {
            return service.listarPorTipo(tipo);
        }
        if (idadeMaxima != null) {
            return service.listarPorIdadeMaxima(idadeMaxima);
        }
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Brinquedo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brinquedo criar(@Valid @RequestBody Brinquedo brinquedo) {
        return service.criar(brinquedo);
    }

    @PostMapping("/lote")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Brinquedo> criarVarios(@RequestBody List<@Valid Brinquedo> lista) {
        return service.criarVarios(lista);
    }

    @PutMapping("/{id}")
    public Brinquedo atualizar(@PathVariable Long id, @Valid @RequestBody Brinquedo dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}