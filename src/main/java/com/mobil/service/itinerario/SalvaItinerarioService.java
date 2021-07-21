package com.mobil.service.itinerario;

import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.integration.util.converter.CoordenadasInToCoordenadasConverter;
import com.mobil.integration.util.converter.ItinerarioInToItinerarioConverter;
import com.mobil.model.entity.Itinerario;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.repository.ItinerarioRepository;
import com.mobil.service.coordenada.SalvaCoordenadaService;
import com.mobil.service.linha.BuscaLinhaIdService;
import com.mobil.util.converter.ItinerarioToItinerarioOutConverter;
import com.mobil.util.validator.ItinerarioInValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Transactional
@Service
public class SalvaItinerarioService {

    private final ItinerarioRepository repository;
    private final ItinerarioInToItinerarioConverter converter;
    private final ItinerarioToItinerarioOutConverter itinerarioToItinerarioOutConverter;
    private final SalvaCoordenadaService salvaCoordenadaService;
    private final BuscaLinhaIdService buscaLinhaIdService;
    private final CoordenadasInToCoordenadasConverter coordenadasInToCoordenadasConverter;
    private final ItinerarioInValidator validator;

    public ItinerarioOut salvar(final ItinerarioIn itinerarioIn) {
        validator.accept(itinerarioIn);

        Itinerario itinerario = converter.convert(itinerarioIn);

        if (exists(itinerarioIn.getCodigo())) {
            itinerario = repository.findByCodigo(itinerarioIn.getCodigo()).get();

            itinerario.setLinha(buscaLinhaIdService.buscar(itinerarioIn.getIdLinha()));
            itinerario.setNome(itinerarioIn.getNome());
            itinerario.setCoordenadas(coordenadasInToCoordenadasConverter.convert(itinerarioIn.getCoordenadas()));
        } else {
            itinerario.setId(getId(itinerarioIn));
        }

        final Itinerario itinerarioSalvo = repository.save(itinerario);

        itinerarioSalvo.setCoordenadas(
                itinerario.getCoordenadas().stream()
                        .map(coordenada -> salvaCoordenadaService.salvar(coordenada))
                        .collect(Collectors.toList())
        );

        return itinerarioToItinerarioOutConverter.convert(itinerarioSalvo);
    }

    private boolean exists(final String codigo) {
        return repository.existsByCodigo(codigo);
    }

    private long getId(final ItinerarioIn itinerarioIn) {

        return repository.findMaxId() + 1;
    }
}
