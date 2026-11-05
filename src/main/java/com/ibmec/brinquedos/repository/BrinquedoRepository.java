package com.ibmec.brinquedos.repository;

import com.ibmec.brinquedos.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {

    List<Brinquedo> findByTipoIgnoreCase(String tipo);
    List<Brinquedo> findByClassificacaoLessThanEqual(Integer idade);
}