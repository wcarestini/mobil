package com.mobil.controller.integration;

import com.mobil.integration.service.linha.CopiaBaseDadosLinhasService;
import com.mobil.model.out.LinhaOut;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/integration/")
public class IntegrationController {

    private final CopiaBaseDadosLinhasService copiaBaseDadosService;

    @ApiOperation(value = "Copia base de dados da API Data Poa.", response = LinhaOut.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Base de dados copiada/atualizada com sucesso"),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @PostMapping("/copy-lines")
    public ResponseEntity<?> copiarLinhas() {
        return ResponseEntity.ok(copiaBaseDadosService.copiarBaseDados());
    }
}
