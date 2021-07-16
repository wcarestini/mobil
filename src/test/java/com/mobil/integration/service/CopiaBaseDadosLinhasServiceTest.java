package com.mobil.integration.service;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.out.LinhaOut;
import com.mobil.util.converter.LinhasToLinhasOutConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CopiaBaseDadosLinhasServiceTest {

    @Mock
    BuscaLinhasService buscaLinhasService;

    @Mock
    SalvaLinhasInService salvaLinhasInService;

    @Mock
    LinhasToLinhasOutConverter linhasToLinhasOutConverter;

    @InjectMocks
    CopiaBaseDadosLinhasService service;

    private List<LinhaIn> linhasIn = new ArrayList<>();

    @Before
    public void setup() {
        linhasIn.add(this.createRandomLinhaIn());
        linhasIn.add(this.createRandomLinhaIn());
        linhasIn.add(this.createRandomLinhaIn());

        when(buscaLinhasService.buscarLinhas()).thenReturn(linhasIn);
    }

    @Test
    public void copiarBaseSucesso() {
        final List<LinhaOut> linhasOut = service.copiarBaseDados();

        verify(buscaLinhasService).buscarLinhas();
        verify(salvaLinhasInService).salvarLinhas(linhasIn);
        verify(linhasToLinhasOutConverter).convert(anyList());

        assertNotNull(linhasOut);
    }

    private LinhaIn createRandomLinhaIn() {
        final LinhaIn linhaIn = new LinhaIn();
        linhaIn.setId(new Random().nextInt());
        linhaIn.setCodigo(RandomString.make());
        linhaIn.setNome(RandomString.make());

        return linhaIn;
    }
}