package com.bankify.repositories;

import com.bankify.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByCuentaOrigenId(Long cuentaOrigenId);
    List<Transferencia> findByCuentaDestinoId(Long cuentaDestinoId);
}
