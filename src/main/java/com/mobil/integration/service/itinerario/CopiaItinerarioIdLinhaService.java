package com.mobil.integration.service.itinerario;

import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.integration.util.converter.ItinerarioInToItinerarioConverter;
import com.mobil.util.converter.ItinerarioToItinerarioOutConverter;
import com.mobil.model.entity.Itinerario;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.repository.ItinerarioRepository;
import com.mobil.service.coordenada.SalvaCoordenadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static com.mobil.util.enums.MensagemErro.ERRO_SALVAR_BANCO;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Transactional
@Service
public class CopiaItinerarioIdLinhaService {

    private final BuscaItinerarioService buscaItinerarioService;
    private final ItinerarioInToItinerarioConverter converter;
    private final ItinerarioRepository repository;
    private final ItinerarioToItinerarioOutConverter itinerarioToItinerarioOutConverter;
    private final SalvaCoordenadaService salvaCoordenada;

    public ItinerarioOut copiarItinerario(final Long idLinha) {
        final ItinerarioIn itinerarioIn = buscaItinerarioService.buscarItinerario(idLinha);
        final Itinerario itinerario = converter.convert(itinerarioIn);

        final Itinerario itinerarioSalvo = ofNullable(repository.save(itinerario)).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ERRO_SALVAR_BANCO.mensagem())
        );

        itinerarioSalvo.setCoordenadas(
                itinerario.getCoordenadas().stream()
                        .map(coordenada -> salvaCoordenada.salvar(coordenada))
                        .collect(Collectors.toList())
        );

        return itinerarioToItinerarioOutConverter.convert(itinerarioSalvo);
    }
}
