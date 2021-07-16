package com.mobil.integration.service;

import com.mobil.integration.model.in.LinhaIn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static com.mobil.integration.util.enums.MensagemErro.ERRO_INESPERADO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BuscaLinhasServiceTest {

    @InjectMocks
    private BuscaLinhasService service;

    @Mock
    private RestTemplate restTemplate;

    private final String url = "url.teste.com";

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "url", url);
    }

    @Test
    public void buscaLinhasSucesso() {
        final LinhaIn linha1 = new LinhaIn();
        linha1.setId(1);

        final LinhaIn linha2 = new LinhaIn();
        linha2.setId(2);

        LinhaIn[] linhasExperadas = new LinhaIn[2];
        linhasExperadas[0] = linha1;
        linhasExperadas[1] = linha2;

        when(restTemplate.getForObject(url, LinhaIn[].class)).thenReturn(linhasExperadas);

        final List<LinhaIn> linhasRetornadas = service.buscarLinhas();

        assertEquals(Arrays.asList(linhasExperadas), linhasRetornadas);
    }

    @Test(expected = ResponseStatusException.class)
    public void buscaLinhasErro() {
        when(restTemplate.getForObject(url, LinhaIn[].class)).thenThrow(new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                ERRO_INESPERADO.mensagem()));

        service.buscarLinhas();
    }
}