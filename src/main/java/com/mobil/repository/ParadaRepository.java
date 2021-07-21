package com.mobil.repository;

import com.mobil.model.entity.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParadaRepository extends JpaRepository<Coordenada, Long> {
}
