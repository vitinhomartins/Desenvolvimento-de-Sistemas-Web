package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.entity.Livro;
import com.biblioteca.biblioteca.entity.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByLeitor(Leitor leitor);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByAutorContainingIgnoreCase(String autor);

    List<Livro> findByGeneroContainingIgnoreCase(String genero);
}