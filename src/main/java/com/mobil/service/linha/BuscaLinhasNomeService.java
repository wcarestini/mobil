package com.mobil.service.linha;

import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
import com.mobil.repository.LinhaRepository;
import com.mobil.util.converter.LinhasToLinhasOutConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuscaLinhasNomeService {

    private final LinhaRepository repository;
    private final LinhasToLinhasOutConverter converter;

    public List<LinhaOut> buscarLinhas(final String nome) {
        final List<Linha> linhas = repository.findAllByNomeContainsIgnoreCase(nome);

        return converter.convert(linhas);
    }
}
