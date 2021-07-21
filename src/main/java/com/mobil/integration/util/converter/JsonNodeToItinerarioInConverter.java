package com.mobil.integration.util.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobil.integration.model.in.CoordenadaIn;
import com.mobil.integration.model.in.ItinerarioIn;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mobil.util.enums.MensagemErro.ERRO_CONVERTER_JSONNODE_ITINERARIO_IN;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
public class JsonNodeToItinerarioInConverter implements Converter<JsonNode, ItinerarioIn> {

    private final ObjectMapper objectMapper;

    @Override
    public ItinerarioIn convert(final JsonNode itinerarioJsonNode) {
        if (isNull(itinerarioJsonNode)) {
            throw new IllegalArgumentException(ERRO_CONVERTER_JSONNODE_ITINERARIO_IN.mensagem());
        }

        final ItinerarioIn itinerarioIn = objectMapper.convertValue(itinerarioJsonNode, ItinerarioIn.class);

        final List<CoordenadaIn> coordenadas = getListaCordenadasIn(itinerarioJsonNode);

        itinerarioIn.setCoordenadas(coordenadas);

        return itinerarioIn;
    }

    private List<CoordenadaIn> getListaCordenadasIn(final JsonNode itinerarioJsonNode) {
        final List<CoordenadaIn> coordenadasIn = new ArrayList<>();

        itinerarioJsonNode.iterator().forEachRemaining(campo -> {
            if (campo.isObject()) {
                final CoordenadaIn coordenadaIn = objectMapper.convertValue(campo, CoordenadaIn.class);

                coordenadasIn.add(coordenadaIn);
            }
        });

        return coordenadasIn;
    }
}
