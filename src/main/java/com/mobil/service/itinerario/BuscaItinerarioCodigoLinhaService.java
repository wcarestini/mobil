package com.mobil.service.itinerario;

import com.mobil.model.entity.Itinerario;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.repository.ItinerarioRepository;
import com.mobil.util.converter.ItinerarioToItinerarioOutConverter;
import com.mobil.util.enums.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.mobil.util.enums.MensagemErro.ERRO_BUSCA_ITINERARIO;

@RequiredArgsConstructor
@Service
public class BuscaItinerarioCodigoLinhaService {

    private final ItinerarioRepository repository;
    private final ItinerarioToItinerarioOutConverter converter;

    public ItinerarioOut buscar(final String codigo) {
        final Itinerario itinerario = repository.findByCodigo(codigo).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        ERRO_BUSCA_ITINERARIO.mensagem()
                ));

        return converter.convert(itinerario);
    }
}
