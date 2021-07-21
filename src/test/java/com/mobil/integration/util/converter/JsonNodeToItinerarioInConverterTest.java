package com.mobil.integration.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobil.integration.model.in.ItinerarioIn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonNodeToItinerarioInConverterTest {

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    JsonNodeToItinerarioInConverter converter;

    private JsonNode jsonNodeItinerario;

    @Before
    public void setup() throws JsonProcessingException {
        jsonNodeItinerario = this.getJsonItinerario();

        when(objectMapper.convertValue(jsonNodeItinerario, ItinerarioIn.class)).thenReturn(new ItinerarioIn());
    }

    @Test
    public void converterSucesso() throws JsonProcessingException {
        final ItinerarioIn itinerarioIn = converter.convert(jsonNodeItinerario);

        verify(objectMapper).convertValue(jsonNodeItinerario, ItinerarioIn.class);

        assertNotNull(itinerarioIn);
        assertNotNull(itinerarioIn.getCoordenadas());
    }

    @Test(expected = IllegalArgumentException.class)
    public void inputNull() {
        converter.convert(null);
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
