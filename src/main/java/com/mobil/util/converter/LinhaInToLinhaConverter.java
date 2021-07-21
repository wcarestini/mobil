package com.mobil.util.converter;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.entity.Linha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LinhaInToLinhaConverter implements Converter<LinhaIn, Linha> {

    private final ModelMapper modelMapper;

    @Override
    public Linha convert(final LinhaIn linhaIn) {
        return modelMapper.map(linhaIn, Linha.class);
    }
}
