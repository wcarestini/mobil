package com.mobil.service.coordenada;

import com.mobil.model.entity.Coordenada;
import com.mobil.repository.CoordenadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SalvaCoordenadaService {

    private final CoordenadaRepository repository;

    public Coordenada salvar(final Coordenada coordenada) {
        return repository.save(coordenada);
    }
}
