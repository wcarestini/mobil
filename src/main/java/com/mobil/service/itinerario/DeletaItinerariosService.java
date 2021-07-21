package com.mobil.service.itinerario;

import com.mobil.model.entity.Itinerario;
import com.mobil.repository.ItinerarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeletaItinerariosService {

    private final ItinerarioRepository repository;

    public void deletarItinerarios(final List<Itinerario> itinerarios) {
        repository.deleteAll(itinerarios);
    }
}
