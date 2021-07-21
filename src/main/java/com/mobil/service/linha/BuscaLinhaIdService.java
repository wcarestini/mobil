package com.mobil.service.linha;

import com.mobil.model.entity.Linha;
import com.mobil.repository.LinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.mobil.util.enums.MensagemErro.ERRO_BUSCA_LINHA;

@RequiredArgsConstructor
@Service
public class BuscaLinhaIdService {

    private final LinhaRepository repository;

    public Linha buscar(final Long idLinha) {
        return repository.findById(idLinha).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        ERRO_BUSCA_LINHA.mensagem()
                ));
    }
}
