package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entity.Leitor;
import com.biblioteca.biblioteca.entity.Usuario;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.biblioteca.repository.LeitorRepository;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeitorService {

    private final LeitorRepository leitorRepository;
    private final UsuarioRepository usuarioRepository;

    public LeitorService(LeitorRepository leitorRepository, UsuarioRepository usuarioRepository) {
        this.leitorRepository = leitorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Leitor criar(Long usuarioId, Leitor leitor) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (usuario.getLeitor() != null) {
            throw new BusinessException("Este usuário já possui um perfil de leitor");
        }

        leitor.setUsuario(usuario);
        return leitorRepository.save(leitor);
    }

    public List<Leitor> listarTodos() {
        return leitorRepository.findAll();
    }

    public Leitor buscarPorId(Long id) {
        return leitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leitor não encontrado"));
    }

    public Leitor atualizar(Long id, Leitor leitorAtualizado) {
        Leitor leitor = buscarPorId(id);

        leitor.setApelido(leitorAtualizado.getApelido());
        leitor.setBiografia(leitorAtualizado.getBiografia());
        leitor.setMetaAnualLivros(leitorAtualizado.getMetaAnualLivros());

        return leitorRepository.save(leitor);
    }

    public void deletar(Long id) {
        Leitor leitor = buscarPorId(id);
        leitorRepository.delete(leitor);
    }
}