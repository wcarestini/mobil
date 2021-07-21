package com.mobil.util.converter;

import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LinhaToLinhaOutConverter implements Converter<Linha, LinhaOut> {

    private final ModelMapper modelMapper;

    @Override
    public LinhaOut convert(final Linha linha) {
        return modelMapper.map(linha, LinhaOut.class);
    }
}
