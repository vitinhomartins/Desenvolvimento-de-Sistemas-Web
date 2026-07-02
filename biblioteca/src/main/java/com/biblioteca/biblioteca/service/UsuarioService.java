package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.entity.Usuario;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException("Já existe um usuário cadastrado com este e-mail");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());

        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}