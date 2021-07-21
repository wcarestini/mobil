package com.mobil.service.linha;

import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
import com.mobil.repository.LinhaRepository;
import com.mobil.util.converter.LinhasToLinhasOutConverter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BuscaLinhasNomeServiceTest {

    @Mock
    LinhaRepository repository;

    @Mock
    LinhasToLinhasOutConverter converter;

    @InjectMocks
    BuscaLinhasNomeService service;

    private String nomeLinha;


    @Before
    public void setup() {
        nomeLinha = RandomStringUtils.randomAlphabetic(10);
    }

    @Test
    public void serviceSucesso() {
        final List<Linha> linhas = new ArrayList<>();
        linhas.add(Linha.builder()
                .id(RandomUtils.nextLong())
                .nome(nomeLinha)
                .codigo(RandomStringUtils.randomAlphanumeric(5))
                .build());

        final List<LinhaOut> linhasOut = new ArrayList<>();
        linhasOut.add(new LinhaOut());

        when(repository.findAllByNomeContainsIgnoreCase(anyString())).thenReturn(linhas);
        when(converter.convert(any())).thenReturn(linhasOut);

        final List<LinhaOut> linhaOuts = service.buscarLinhas(nomeLinha);

        verify(repository).findAllByNomeContainsIgnoreCase(nomeLinha);
        verify(converter).convert(linhas);

        assertNotNull(linhaOuts);
        assertEquals(linhas.size(), linhaOuts.size());
    }
}