package com.mobil.repository;

import com.mobil.model.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    boolean existsByCodigo(final String codigo);

    @Query(value = "select id_itinerario from itinerarios order by id_itinerario desc limit 1",
            nativeQuery = true)
    long findMaxId();

    Optional<Itinerario> findByCodigo(final String codigo);
}
