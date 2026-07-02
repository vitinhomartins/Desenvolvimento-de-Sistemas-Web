package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entity.Leitor;
import com.biblioteca.biblioteca.entity.Livro;
import com.biblioteca.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.biblioteca.repository.LeitorRepository;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LeitorRepository leitorRepository;

    public LivroService(LivroRepository livroRepository, LeitorRepository leitorRepository) {
        this.livroRepository = livroRepository;
        this.leitorRepository = leitorRepository;
    }

    public Livro criar(Long leitorId, Livro livro) {
        Leitor leitor = leitorRepository.findById(leitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Leitor não encontrado"));

        livro.setLeitor(leitor);
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public List<Livro> listarPorLeitor(Long leitorId) {
        Leitor leitor = leitorRepository.findById(leitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Leitor não encontrado"));

        return livroRepository.findByLeitor(leitor);
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {
        Livro livro = buscarPorId(id);

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setGenero(livroAtualizado.getGenero());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livro.setStatusLeitura(livroAtualizado.getStatusLeitura());
        livro.setNota(livroAtualizado.getNota());
        livro.setComentario(livroAtualizado.getComentario());

        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        Livro livro = buscarPorId(id);
        livroRepository.delete(livro);
    }
}