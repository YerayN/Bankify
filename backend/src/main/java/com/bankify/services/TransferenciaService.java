package com.bankify.services;

import com.bankify.models.Cuenta;
import com.bankify.models.Transferencia;
import com.bankify.repositories.CuentaRepository;
import com.bankify.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Transferencia realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto) {
        // Buscar las cuentas en la base de datos
        Optional<Cuenta> cuentaOrigenOpt = cuentaRepository.findById(cuentaOrigenId);
        Optional<Cuenta> cuentaDestinoOpt = cuentaRepository.findById(cuentaDestinoId);

        // Validar que ambas cuentas existan
        if (cuentaOrigenOpt.isEmpty() || cuentaDestinoOpt.isEmpty()) {
            throw new RuntimeException("Una de las cuentas no existe.");
        }

        Cuenta cuentaOrigen = cuentaOrigenOpt.get();
        Cuenta cuentaDestino = cuentaDestinoOpt.get();

        // ✅ Verificar si la cuenta origen tiene suficiente saldo
        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new RuntimeException("Saldo insuficiente en la cuenta de origen.");
        }

        // 🔄 Realizar la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto)); // Restar saldo
        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto)); // Sumar saldo

        // Guardar los cambios en la base de datos
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        // Crear y guardar la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setCuentaOrigen(cuentaOrigen);
        transferencia.setCuentaDestino(cuentaDestino);
        transferencia.setMonto(monto);
        transferencia.setFecha(LocalDateTime.now());

        return transferenciaRepository.save(transferencia);
    }
}
