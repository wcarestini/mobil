package com.mobil.util.converter;

import com.mobil.model.entity.Itinerario;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.util.enums.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.mobil.util.enums.MensagemErro.ERRO_CONVERTER_ITINERARIO_ITINERARIO_OUT;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
public class ItinerarioToItinerarioOutConverter implements Converter<Itinerario, ItinerarioOut> {

    private final ModelMapper modelMapper;

    @Override
    public ItinerarioOut convert(final Itinerario itinerario) {

        if (isNull(itinerario)) {
            throw new IllegalArgumentException(ERRO_CONVERTER_ITINERARIO_ITINERARIO_OUT.mensagem());
        }

        return modelMapper.map(itinerario, ItinerarioOut.class);
    }
}
