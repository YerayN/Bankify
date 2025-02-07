package com.bankify.controllers;

import com.bankify.models.Transferencia;
import com.bankify.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    // Endpoint para realizar una transferencia
    @PostMapping
    public Transferencia realizarTransferencia(@RequestParam Long cuentaOrigenId,
                                               @RequestParam Long cuentaDestinoId,
                                               @RequestParam BigDecimal monto) {
        return transferenciaService.realizarTransferencia(cuentaOrigenId, cuentaDestinoId, monto);
    }

    // Endpoint para obtener una transferencia por ID
    @GetMapping("/{id}")
    public Optional<Transferencia> obtenerTransferenciaPorId(@PathVariable Long id) {
    return transferenciaService.obtenerTransferenciaPorId(id);
}
}
