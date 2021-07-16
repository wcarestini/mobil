package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.entity.Linha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LinhasInToLinhasConverterTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    LinhasInToLinhasConverter converter;

    @Test
    public void converterSucesso() {
        final LinhaIn linhaIn1 = new LinhaIn();
        linhaIn1.setId(1l);
        linhaIn1.setNome("linhaIn1");
        linhaIn1.setCodigo("codigo1");

        final LinhaIn linhaIn2 = new LinhaIn();
        linhaIn2.setId(2l);
        linhaIn2.setNome("linhaIn2");
        linhaIn2.setCodigo("codigo2");

        final List<LinhaIn> linhasIn = new ArrayList<>();
        linhasIn.add(linhaIn1);
        linhasIn.add(linhaIn2);

        linhasIn.forEach(linhaIn -> {
            final Linha linha = new Linha();
            linha.setId(linhaIn.getId());
            linha.setNome(linhaIn.getNome());
            linha.setCodigo(linhaIn.getCodigo());

            when(modelMapper.map(linhaIn, Linha.class)).thenReturn(linha);
        });

        final List<Linha> linhasRetornado = converter.convert(linhasIn);

        assertNotNull(linhasRetornado);
        assertEquals(linhasIn.size(), linhasRetornado.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void converterErro() {
        converter.convert(new ArrayList<>());
    }
}
