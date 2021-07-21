package com.mobil.util.converter;

import com.mobil.model.entity.Itinerario;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.model.out.LinhaOut;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItinerarioToItinerarioOutConverterTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ItinerarioToItinerarioOutConverter converter;

    private Itinerario itinerario;

    @Before
    public void setup() {
        itinerario = getItinerario();
    }

    @Test
    public void converterSucesso() {
        final ItinerarioOut itinerarioOutEsperado = new ItinerarioOut();
        itinerarioOutEsperado.setCodigo(itinerario.getCodigo());
        itinerarioOutEsperado.setNome(itinerario.getNome());
        itinerarioOutEsperado.setLinha(new LinhaOut());
        itinerarioOutEsperado.setCoordenadas(new ArrayList<>());

        when(modelMapper.map(itinerario, ItinerarioOut.class)).thenReturn(itinerarioOutEsperado);

        final ItinerarioOut itinerarioOutRetornado = converter.convert(itinerario);

        verify(modelMapper).map(itinerario, ItinerarioOut.class);

        assertEquals(itinerarioOutEsperado, itinerarioOutRetornado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itinerarioNull() {
        converter.convert(null);
    }

    private Itinerario getItinerario() {
        return Itinerario.builder()
                .id(RandomUtils.nextLong())
                .codigo(RandomStringUtils.randomAlphanumeric(5))
                .nome(RandomStringUtils.randomAlphabetic(15))
                .build();
    }
}