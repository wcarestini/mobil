package com.mobil.util.converter;

import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LinhasToLinhasOutConverter implements Converter<List<Linha>, List<LinhaOut>> {

    private final ModelMapper modelMapper;

    @Override
    public List<LinhaOut> convert(final List<Linha> linhas) {

        if (linhas.isEmpty()) {
            throw new IllegalArgumentException("A lista de LinhaIn não pode ser convertidade em uma lista de Linha.");
        }

        return linhas.stream().map(linha -> modelMapper.map(linha, LinhaOut.class)).collect(Collectors.toList());
    }
}
