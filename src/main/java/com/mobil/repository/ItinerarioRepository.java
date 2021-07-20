package com.mobil.repository;

import com.mobil.model.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    boolean existsByCodigo(final String codigo);
}
