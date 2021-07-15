package com.mobil.repository;

import com.mobil.model.entity.Linha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinhaRepository extends JpaRepository<Linha, Long> {

    List<Linha> findByNome(final String nome);
}
