package com.mobil.service.linha;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.integration.util.converter.LinhasInToLinhasConverter;
import com.mobil.model.entity.Linha;
import com.mobil.repository.LinhaRepository;
import com.mobil.service.linha.SalvaLinhasInService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalvaLinhasInServiceTest {

    @Mock
    LinhaRepository repository;

    @Mock
    LinhasInToLinhasConverter linhasInToLinhasConverter;

    @InjectMocks
    SalvaLinhasInService service;

    private List<LinhaIn> linhasIn = new ArrayList<>();

    private List<Linha> linhas;

    @Before
    public void setup() {
        linhasIn.add(this.createRandomLinhaIn());
        linhasIn.add(this.createRandomLinhaIn());
        linhasIn.add(this.createRandomLinhaIn());

        linhas = new LinhasInToLinhasConverter(new ModelMapper()).convert(linhasIn);

        when(linhasInToLinhasConverter.convert(anyList()))
                .thenReturn(linhas);
    }

    @Test
    public void salvarLinhasSucesso() {
        final List<Linha> linhasRetornadas = service.salvarLinhas(linhasIn);

        verify(linhasInToLinhasConverter).convert(linhasIn);

        linhasIn.forEach(linhaIn -> {
            verify(repository).existsByCodigo(linhaIn.getCodigo());
        });

        verify(repository).saveAll(linhas);

        assertNotNull(linhasRetornadas);
    }

    private LinhaIn createRandomLinhaIn() {
        final LinhaIn linhaIn = new LinhaIn();
        linhaIn.setId(new Random().nextInt());
        linhaIn.setCodigo(RandomString.make());
        linhaIn.setNome(RandomString.make());

        return linhaIn;
    }
}
