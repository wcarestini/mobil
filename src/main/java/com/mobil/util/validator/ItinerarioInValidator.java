package com.mobil.util.validator;

import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.util.enums.MensagemErro;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

@Component
public class ItinerarioInValidator implements Consumer<ItinerarioIn> {
    @Override
    public void accept(final ItinerarioIn itinerarioIn) {
        if(isNull(itinerarioIn.getIdLinha())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MensagemErro.ERRO_ID_LINHA_NAO_INFORMADO.mensagem());
        }

        if (isNull(itinerarioIn.getCodigo())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MensagemErro.ERRO_CODIGO_NAO_INFORMADO.mensagem()
            );
        }
    }
}
