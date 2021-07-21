package com.mobil.service.itinerario;

import com.mobil.repository.ItinerarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletaItinerarioService {

    private final ItinerarioRepository repository;

    public void deletar(final Long idItinerario) {
        repository.deleteById(idItinerario);
    }
}
