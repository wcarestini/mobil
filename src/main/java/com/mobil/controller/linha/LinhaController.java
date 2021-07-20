package com.mobil.controller.linha;

import com.mobil.model.out.LinhaOut;
import com.mobil.service.linha.BuscaLinhasNomeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/line/")
public class LinhaController {

    private final BuscaLinhasNomeService buscaLinhasNomeService;

    @ApiOperation(value = "Busca, linhas que contém o nome informado.", response = LinhaOut[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @GetMapping("/{nomeLinha}")
    public ResponseEntity<?> buscarLinhas(@PathVariable final String nomeLinha) {
        return ResponseEntity.ok(buscaLinhasNomeService.buscarLinhas(nomeLinha));
    }
}
