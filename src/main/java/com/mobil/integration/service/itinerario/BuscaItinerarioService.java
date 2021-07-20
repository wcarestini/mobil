package com.mobil.integration.service.itinerario;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.integration.util.converter.JsonNodeToItinerarioInConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static com.mobil.util.enums.MensagemErro.ERRO_INESPERADO;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class BuscaItinerarioService {

    @Value("${integration.busca-itinerario.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final JsonNodeToItinerarioInConverter converter;

    public ItinerarioIn buscarItinerario(final Long idLinha) {

        final JsonNode itinerariosJsonNode = ofNullable(restTemplate.getForObject(
                url + idLinha,
                JsonNode.class))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        ERRO_INESPERADO.mensagem()));

        return converter.convert(itinerariosJsonNode);
    }
}
