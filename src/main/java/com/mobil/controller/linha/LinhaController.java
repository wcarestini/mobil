package com.mobil.controller.linha;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.out.LinhaOut;
import com.mobil.service.linha.BuscaLinhasNomeService;
import com.mobil.service.linha.DeletaLinhaService;
import com.mobil.service.linha.SalvaLinhaInService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/line/")
public class LinhaController {

    private final BuscaLinhasNomeService buscaLinhasNomeService;
    private final SalvaLinhaInService salvaLinhaInService;
    private final DeletaLinhaService deletaLinhaService;

    @ApiOperation(value = "Busca, linhas que contém o nome informado.", response = LinhaOut[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @GetMapping("/{nomeLinha}")
    public ResponseEntity<?> buscarLinhas(@PathVariable final String nomeLinha) {
        return ResponseEntity.ok(buscaLinhasNomeService.buscarLinhas(nomeLinha));
    }

    @ApiOperation(value = "Salva/atualizada linha informada no corpo da requisição.", response = LinhaOut.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Linha salva/atualizada com sucesso"),
            @ApiResponse(code = 500, message = "Erro inesperado")
    })
    @PostMapping
    public ResponseEntity<?> salvarLinha(@RequestBody final LinhaIn linha) {
        return ResponseEntity.ok(salvaLinhaInService.salvar(linha));
    }

    @ApiOperation(value = "Deleta linha com id informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Linha deletada com sucesso."),
            @ApiResponse(code = 404, message = "Nenhuma linha com id informado."),
            @ApiResponse(code = 500, message = "Erro inesperado")
    })
    @DeleteMapping("/{idLinha}")
    public ResponseEntity<Void> deletarLinha(@PathVariable final Long idLinha) {
        deletaLinhaService.deletar(idLinha);

        return ResponseEntity.ok().build();
    }
}
