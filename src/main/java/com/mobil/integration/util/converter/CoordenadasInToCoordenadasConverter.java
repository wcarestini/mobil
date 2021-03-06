package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.CoordenadaIn;
import com.mobil.model.entity.Coordenada;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CoordenadasInToCoordenadasConverter implements Converter<List<CoordenadaIn>, List<Coordenada>> {

    private final ModelMapper modelMapper;

    @Override
    public List<Coordenada> convert(final List<CoordenadaIn> coordenadasIn) {
        return coordenadasIn.stream()
                .map(coordenadaIn -> modelMapper.map(coordenadaIn, Coordenada.class))
                .collect(Collectors.toList());
    }
}
