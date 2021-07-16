package com.mobil.integration.service;

import com.mobil.integration.model.in.LinhaIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mobil.integration.util.enums.MensagemErro.ERRO_INESPERADO;

@RequiredArgsConstructor
@Service
public class BuscaLinhasService {

    @Value("${integration.lista-linhas.url}")
    private String url;

    private final RestTemplate restTemplate;

    public List<LinhaIn> buscarLinhas() {
        return Arrays.asList(
                Optional.ofNullable(restTemplate.getForObject(
                        url,
                        LinhaIn[].class))
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.BAD_GATEWAY,
                                ERRO_INESPERADO.mensagem())));
    }
}
