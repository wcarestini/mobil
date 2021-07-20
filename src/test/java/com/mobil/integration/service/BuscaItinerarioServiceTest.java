package com.mobil.integration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.integration.service.itinerario.BuscaItinerarioService;
import com.mobil.integration.util.converter.JsonNodeToItinerarioInConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BuscaItinerarioServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    JsonNodeToItinerarioInConverter converter;

    @InjectMocks
    BuscaItinerarioService service;

    private final String url = "url.teste.com";
    private Long idLinha;
    private JsonNode jsonNodeItinerario;

    @Before
    public void setup() throws JsonProcessingException {
        jsonNodeItinerario = this.getJsonItinerario();
        ReflectionTestUtils.setField(service, "url", url);

        idLinha = new Random().nextLong();
    }

    @Test
    public void buscaItinerarioSucesso() {
        when(restTemplate.getForObject(url + idLinha, JsonNode.class)).thenReturn(jsonNodeItinerario);
        when(converter.convert(jsonNodeItinerario)).thenReturn(new ItinerarioIn());

        final ItinerarioIn itinerarioIn = service.buscarItinerario(idLinha);

        verify(restTemplate).getForObject(url + idLinha, JsonNode.class);
        verify(converter).convert(jsonNodeItinerario);

        assertNotNull(itinerarioIn);
    }

    @Test(expected = ResponseStatusException.class)
    public void buscaItinerarioErroConverter() {
        service.buscarItinerario(idLinha);

        verify(restTemplate).getForObject(url + idLinha, JsonNode.class);
        verify(converter).convert(any());
    }

    @Test(expected = ResponseStatusException.class)
    public void buscaItinerarioErroRequisicaoApi() {
        when(restTemplate.getForObject(url + idLinha, JsonNode.class))
                .thenReturn(null);

        service.buscarItinerario(idLinha);

        verifyNoInteractions(converter);
    }

    private JsonNode getJsonItinerario() throws JsonProcessingException {
        return new ObjectMapper().readTree("{\n" +
                "                \"idlinha\": \"5312\",\n" +
                "                \"nome\": \"AGOSTINHO\\/MAIAS\\/RADIO FARROUPILHA\",\n" +
                "                \"codigo\": \"E16-1\",\n" +
                "                \"0\": {\n" +
                "                  \"lat\": \"-29.99583057730300000\",\n" +
                "                  \"lng\": \"-51.09143710938000000\"\n" +
                "                }}");
    }
}