package com.mobil.service.linha;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.integration.util.converter.LinhasInToLinhasConverter;
import com.mobil.model.entity.Linha;
import com.mobil.repository.LinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SalvaLinhasInService {

    private final LinhaRepository repository;
    private final LinhasInToLinhasConverter linhasInToLinhasConverter;

    public List<Linha> salvarLinhas(final List<LinhaIn> linhasIn) {

        final List<Linha> linhas = linhasInToLinhasConverter.convert(linhasIn);

        final List<Linha> linhasFiltradas = filtrarLinhas(linhas);

        return repository.saveAll(linhasFiltradas);
    }

    private List<Linha> filtrarLinhas(final List<Linha> linhas) {
        return Objects.requireNonNull(linhas).stream()
                .filter(linha -> !repository.existsByCodigo(linha.getCodigo())).collect(Collectors.toList());
    }
}
