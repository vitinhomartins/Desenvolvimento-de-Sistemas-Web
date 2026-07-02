package com.biblioteca.biblioteca.security;

import com.biblioteca.biblioteca.dto.LoginRequestDTO;
import com.biblioteca.biblioteca.dto.LoginResponseDTO;
import com.biblioteca.biblioteca.entity.Usuario;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import com.biblioteca.biblioteca.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new BusinessException("E-mail ou senha inválidos"));

        boolean senhaCorreta = passwordEncoder.matches(loginRequest.senha(), usuario.getSenha());

        if (!senhaCorreta) {
            throw new BusinessException("E-mail ou senha inválidos");
        }

        String token = jwtService.gerarToken(usuario);

        return new LoginResponseDTO(token);
    }
}