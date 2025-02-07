package com.bankify.services;

import com.bankify.models.Usuario;
import com.bankify.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario registrarUsuario(Usuario usuario) {
    // Verificar si el DNI ya existe
    if (usuarioRepository.findByDni(usuario.getDni()).isPresent()) {
        throw new RuntimeException("El DNI ya está registrado en el sistema.");
    }

    // Verificar si el email ya existe
    if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
        throw new RuntimeException("El email ya está registrado en el sistema.");
    }

    usuario.setFechaRegistro(LocalDateTime.now()); // Agregar fecha de registro
    usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword())); // Cifrar contraseña
    
    return usuarioRepository.save(usuario); // Guardar en la BD
}

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
}
