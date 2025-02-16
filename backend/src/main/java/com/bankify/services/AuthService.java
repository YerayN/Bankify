package com.bankify.services;

import com.bankify.dto.LoginRequest;
import com.bankify.models.Usuario;
import com.bankify.repositories.UsuarioRepository;
import com.bankify.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String login(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());
        
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
    
        Usuario usuario = usuarioOpt.get();
    
        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
    
        // Convertir el usuario en un UserDetails antes de generar el token
        UserDetails userDetails = User.builder()
            .username(usuario.getEmail())
            .password(usuario.getPassword())
            .roles("USER") // Puedes cambiarlo si necesitas roles
            .build();
    
        return jwtUtil.generateToken(userDetails);
    }
}
