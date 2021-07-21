package com.mobil.service.linha;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
import com.mobil.repository.LinhaRepository;
import com.mobil.util.converter.LinhaInToLinhaConverter;
import com.mobil.util.converter.LinhaToLinhaOutConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SalvaLinhaInService {

    private final LinhaInToLinhaConverter linhaInToLinhaConverter;
    private final LinhaToLinhaOutConverter linhaToLinhaOutConverter;
    private final LinhaRepository repository;

    public LinhaOut salvar(final LinhaIn linhaIn) {
        linhaIn.setId(getId(linhaIn));
        final Linha linhaSalva = repository.save(linhaInToLinhaConverter.convert(linhaIn));

        return linhaToLinhaOutConverter.convert(linhaSalva);
    }

    private Long getId(final LinhaIn linhaIn) {
        if (linhaIn.getId() == 0) {
            return repository.findMaxId() + 1;
        }

        return linhaIn.getId();
    }
}
