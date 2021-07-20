package com.mobil.repository;

import com.mobil.model.entity.Linha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinhaRepository extends JpaRepository<Linha, Long> {

    List<Linha> findByNome(final String nome);

    boolean existsByCodigo(final String codigo);

    Optional<Linha> findByCodigo(final String codigo);

    List<Linha> findAllByNomeContainsIgnoreCase(final String nome);
}
