package com.bankify.services;

import com.bankify.models.Cuenta;
import com.bankify.models.Usuario;
import com.bankify.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Random;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta crearCuentaParaUsuario(Usuario usuario) {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(usuario);
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setNumeroCuenta(generarNumeroCuenta());

        return cuentaRepository.save(cuenta);
    }

    private String generarNumeroCuenta() {
        Random random = new Random();
        String numeroCuenta;
        do {
            numeroCuenta = "ES" + (1000000000 + random.nextInt(900000000));
        } while (cuentaRepository.existsByNumeroCuenta(numeroCuenta)); // Verifica si ya existe
        return numeroCuenta;
    }    
}
