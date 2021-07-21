package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.model.entity.Itinerario;
import com.mobil.model.entity.Linha;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItinerarioInToItinerarioConverterTest {

    @Mock
    ModelMapper modelMapper;

    @Mock
    CoordenadasInToCoordenadasConverter coordenadasInToCoordenadasConverter;

    @InjectMocks
    ItinerarioInToItinerarioConverter converter;

    private ItinerarioIn itinerarioIn;

    @Before
    public void setup() {
        itinerarioIn = getRandomItinerarioIn();
    }

    @Test
    public void converterSucesso() {
        final Itinerario itinerarioEsperado = Itinerario.builder()
                .linha(Linha.builder().id(itinerarioIn.getIdLinha()).build())
                .nome(itinerarioIn.getNome())
                .codigo(itinerarioIn.getCodigo())
                .build();

        when(modelMapper.map(itinerarioIn, Itinerario.class)).thenReturn(itinerarioEsperado);

        final Itinerario itinerarioRetornado = converter.convert(itinerarioIn);

        verify(modelMapper).map(itinerarioIn, Itinerario.class);
        verify(coordenadasInToCoordenadasConverter).convert(itinerarioIn.getCoordenadas());

        assertEquals(itinerarioEsperado, itinerarioRetornado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void converterParametroNull() {
        converter.convert(null);
    }

    private ItinerarioIn getRandomItinerarioIn() {
        final ItinerarioIn itinerario = new ItinerarioIn();
        itinerario.setCoordenadas(new ArrayList<>());
        itinerario.setNome(RandomStringUtils.randomAlphabetic(10));
        itinerario.setCodigo(RandomStringUtils.randomAlphabetic(5));

        return itinerario;
    }
}