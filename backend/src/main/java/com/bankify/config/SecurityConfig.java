package com.bankify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para pruebas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/usuarios/registrar").permitAll() // Permitir registro sin autenticación
                .requestMatchers("/api/usuarios/{id}").permitAll() // Permitir obtener usuario sin autenticación
                .anyRequest().authenticated() // Otras rutas requieren autenticación
            );

        return http.build();
    }
}
