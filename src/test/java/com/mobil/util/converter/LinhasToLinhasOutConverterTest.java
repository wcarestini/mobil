package com.mobil.util.converter;

import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
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
public class LinhasToLinhasOutConverterTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    LinhasToLinhasOutConverter converter;

    @Test
    public void converterSucesso() {
        final Linha linha1 = new Linha();
        linha1.setId(1l);
        linha1.setNome("linha1");
        linha1.setCodigo("codigo1");

        final Linha linha2 = new Linha();
        linha2.setId(2l);
        linha2.setNome("linha2");
        linha2.setCodigo("codigo2");

        final List<Linha> linhas = new ArrayList<>();
        linhas.add(linha1);
        linhas.add(linha2);

        linhas.forEach(linha -> {
            final LinhaOut linhaOut = new LinhaOut();
            linhaOut.setNome(linha.getNome());
            linhaOut.setCodigo(linha.getCodigo());

            when(modelMapper.map(linha, LinhaOut.class)).thenReturn(linhaOut);
        });

        final List<LinhaOut> linhasOutRetornado = converter.convert(linhas);

        assertNotNull(linhasOutRetornado);
        assertEquals(linhas.size(), linhasOutRetornado.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void converterErro() {
        converter.convert(new ArrayList<>());
    }
}
