package com.mobil.integration.util.converter;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.entity.Linha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.mobil.util.enums.MensagemErro.ERRO_CONVERTER_LINHAS_IN_LINHAS;

@RequiredArgsConstructor
@Component
public class LinhasInToLinhasConverter implements Converter<List<LinhaIn>, List<Linha>> {

    private final ModelMapper modelMapper;

    @Override
    public List<Linha> convert(final List<LinhaIn> linhasIn) {

        if (linhasIn.isEmpty()) {
            throw new IllegalArgumentException(ERRO_CONVERTER_LINHAS_IN_LINHAS.mensagem());
        }

        return linhasIn.stream().map(linhaIn -> modelMapper.map(linhaIn, Linha.class)).collect(Collectors.toList());
    }
}
