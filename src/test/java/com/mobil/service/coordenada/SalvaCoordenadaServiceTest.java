package com.mobil.service.coordenada;

import com.mobil.model.entity.Coordenada;
import com.mobil.repository.CoordenadaRepository;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalvaCoordenadaServiceTest {

    @Mock
    CoordenadaRepository repository;

    @InjectMocks
    SalvaCoordenadaService service;

    private Coordenada coordenada;

    @Before
    public void setup() {
        coordenada = getRandomCoordenada();
    }

    @Test
    public void salvaCoordenadaSucesso() {
        when(repository.save(coordenada)).thenReturn(coordenada);

        final Coordenada coordenadaRetornada = service.salvar(coordenada);

        verify(repository).save(coordenada);

        assertEquals(coordenada, coordenadaRetornada);
    }

    private Coordenada getRandomCoordenada() {
        return Coordenada.builder()
                .id(RandomUtils.nextLong())
                .itinerarios(new ArrayList<>())
                .latitude(RandomUtils.nextDouble())
                .longitude(RandomUtils.nextDouble())
                .build();
    }
}