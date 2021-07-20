package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.CoordenadaIn;
import com.mobil.model.entity.Coordenada;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoordenadasInToCoordenadasConverterTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CoordenadasInToCoordenadasConverter converter;

    private List<CoordenadaIn> coordenadasIn = new ArrayList<>();

    @Before
    public void setup() {
        coordenadasIn.add(getRandomCoordenadaIn());
        coordenadasIn.add(getRandomCoordenadaIn());
    }

    @Test
    public void converterSucesso() {

        coordenadasIn.forEach(coordenadaIn ->
                when(modelMapper.map(coordenadaIn, Coordenada.class)).thenReturn(Coordenada.builder()
                        .latitude(coordenadaIn.getLatitude())
                        .longitude(coordenadaIn.getLongitude())
                        .build())
        );

        final List<Coordenada> coordenadasRetornada = converter.convert(coordenadasIn);

        coordenadasIn.forEach(coordenadaIn ->
                verify(modelMapper).map(coordenadaIn, Coordenada.class)
        );

        assertNotNull(coordenadasRetornada);
        assertEquals(coordenadasIn.size(), coordenadasRetornada.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parametroEmpty() {
        converter.convert(new ArrayList<>());
    }

    private CoordenadaIn getRandomCoordenadaIn() {
        final CoordenadaIn coordenadaIn = new CoordenadaIn();
        coordenadaIn.setLongitude(RandomUtils.nextDouble());
        coordenadaIn.setLatitude(RandomUtils.nextDouble());

        return coordenadaIn;
    }
}