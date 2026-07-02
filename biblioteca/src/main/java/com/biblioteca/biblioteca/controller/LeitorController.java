package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entity.Leitor;
import com.biblioteca.biblioteca.service.LeitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leitores")
public class LeitorController {

    private final LeitorService leitorService;

    public LeitorController(LeitorService leitorService) {
        this.leitorService = leitorService;
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Leitor> criar(@PathVariable Long usuarioId, @Valid @RequestBody Leitor leitor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leitorService.criar(usuarioId, leitor));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(leitorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leitor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(leitorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leitor> atualizar(@PathVariable Long id, @Valid @RequestBody Leitor leitor) {
        return ResponseEntity.ok(leitorService.atualizar(id, leitor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        leitorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}