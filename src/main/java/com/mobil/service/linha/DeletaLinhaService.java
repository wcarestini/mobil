package com.mobil.service.linha;

import com.mobil.model.entity.Linha;
import com.mobil.repository.LinhaRepository;
import com.mobil.service.itinerario.DeletaItinerariosService;
import com.mobil.util.enums.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import static com.mobil.util.enums.MensagemErro.ERRO_BUSCA_LINHA;

@RequiredArgsConstructor
@Transactional
@Service
public class DeletaLinhaService {

    private final LinhaRepository repository;
    private final DeletaItinerariosService deletaItinerariosService;

    public void deletar(final Long idLinha) {
        final Linha linha = repository.findById(idLinha).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        ERRO_BUSCA_LINHA.mensagem())
        );

        deletaItinerariosService.deletarItinerarios(linha.getItinerarios());

        repository.delete(linha);
    }
}
