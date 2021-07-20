package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.model.entity.Itinerario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import static com.mobil.util.enums.MensagemErro.ERRO_CONVERTER_ITINERARIOIN_ITINERARIO;
import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Component
public class ItinerarioInToItinerarioConverter implements Converter<ItinerarioIn, Itinerario> {

    private final ModelMapper modelMapper;
    private final CoordenadasInToCoordenadasConverter coordenadasInToCoordenadasConverter;

    @Override
    public Itinerario convert(final ItinerarioIn itinerarioIn) {

        if (isNull(itinerarioIn)) {
            throw new IllegalArgumentException(ERRO_CONVERTER_ITINERARIOIN_ITINERARIO.mensagem());
        }

        final Itinerario itinerario = modelMapper.map(itinerarioIn, Itinerario.class);

        itinerario.setCoordenadas(coordenadasInToCoordenadasConverter.convert(itinerarioIn.getCoordenadas()));
        itinerario.setCoordenadas(itinerario.getCoordenadas().stream()
                .map(coordenada -> {
                    coordenada.setItinerarios(singletonList(Itinerario.builder().id(itinerario.getId()).build()));
                    return coordenada;
                })
                .collect(Collectors.toList()));

        return itinerario;
    }
}
