package com.mobil.controller.integration;

import com.mobil.integration.service.itinerario.CopiaItinerarioIdLinhaService;
import com.mobil.integration.service.linha.CopiaBaseDadosLinhasService;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.model.out.LinhaOut;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/integration/")
public class IntegrationController {

    private final CopiaBaseDadosLinhasService copiaBaseDadosService;
    private final CopiaItinerarioIdLinhaService copiaItinerarioIdLinhaService;

    @ApiOperation(value = "Copia base de dados de linhas da API Data Poa.", response = LinhaOut.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Base de dados copiada/atualizada com sucesso"),
            @ApiResponse(code = 500, message = "Erro inesperado."),
            @ApiResponse(code = 502, message = "Erro ao se conectar com a API Data Poa.")
    })
    @PostMapping("/copy-lines")
    public ResponseEntity<?> copiarLinhas() {
        return ResponseEntity.ok(copiaBaseDadosService.copiarBaseDados());
    }

    @ApiOperation(value = "Copia itinerário da base de dados da API Data Poa.", response = ItinerarioOut.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Itinerário copiado/atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Erro inesperado."),
            @ApiResponse(code = 502, message = "Erro ao se conectar com a API Data Poa.")
    })
    @PostMapping("/copy-itinerary/{idLinha}")
    public ResponseEntity<?> copiarItinerario(@PathVariable final Long idLinha) {
        return ResponseEntity.ok(copiaItinerarioIdLinhaService.copiarItinerario(idLinha));
    }
}
