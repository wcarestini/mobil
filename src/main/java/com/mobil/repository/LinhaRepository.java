package com.mobil.repository;

import com.mobil.model.entity.Linha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LinhaRepository extends JpaRepository<Linha, Long> {

    List<Linha> findByNome(final String nome);

    boolean existsByCodigo(final String codigo);

    Optional<Linha> findByCodigo(final String codigo);

    List<Linha> findAllByNomeContainsIgnoreCase(final String nome);

    @Query(value = "select id_linha from linhas order by id_linha desc limit 1",
            nativeQuery = true)
    Long findMaxId();
}
