package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.entity.Linha;
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
        final LinhaIn linhaIn1 = createRandomLinhaIn();

        final LinhaIn linhaIn2 = createRandomLinhaIn();

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

    private LinhaIn createRandomLinhaIn() {
        final LinhaIn linhaIn = new LinhaIn();
        linhaIn.setId(new Random().nextInt());
        linhaIn.setCodigo(RandomString.make());
        linhaIn.setNome(RandomString.make());

        return linhaIn;
    }
}
