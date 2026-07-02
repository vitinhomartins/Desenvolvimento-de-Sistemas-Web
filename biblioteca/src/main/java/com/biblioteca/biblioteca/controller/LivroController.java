package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.entity.Livro;
import com.biblioteca.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/leitor/{leitorId}")
    public ResponseEntity<Livro> criar(@PathVariable Long leitorId, @Valid @RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criar(leitorId, livro));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/leitor/{leitorId}")
    public ResponseEntity<?> listarPorLeitor(@PathVariable Long leitorId) {
        return ResponseEntity.ok(livroService.listarPorLeitor(leitorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
        return ResponseEntity.ok(livroService.atualizar(id, livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}