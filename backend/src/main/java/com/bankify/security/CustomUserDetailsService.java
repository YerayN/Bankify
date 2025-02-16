package com.bankify.security;

import com.bankify.models.Usuario;
import com.bankify.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }

        Usuario usuario = usuarioOpt.get();

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) // Asegúrate de que la contraseña esté encriptada con BCrypt
                .roles("USER") // Puedes cambiar esto si tienes roles en la BD
                .build();
    }
}
