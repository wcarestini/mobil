package com.mobil.controller.itinerario;

import com.mobil.integration.model.in.ItinerarioIn;
import com.mobil.model.out.ItinerarioOut;
import com.mobil.service.itinerario.BuscaItinerarioCodigoLinhaService;
import com.mobil.service.itinerario.DeletaItinerarioService;
import com.mobil.service.itinerario.SalvaItinerarioService;
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
@RequestMapping("/itinerary/")
public class ItinerarioController {

    private final SalvaItinerarioService salvaItinerarioService;
    private final BuscaItinerarioCodigoLinhaService buscaItinerarioCodigoLinhaService;
    private final DeletaItinerarioService deletaItinerarioService;

    @ApiOperation(value = "Salva/atualiza itinerario.", response = ItinerarioOut.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Itinerario salvo/atualizado com sucesso."),
            @ApiResponse(code = 404, message = "Linha não encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @PostMapping
    public ResponseEntity<?> salvarItinerario(@RequestBody final ItinerarioIn itinerario) {
        return ResponseEntity.ok(salvaItinerarioService.salvar(itinerario));
    }

    @ApiOperation(value = "Busca itinerário por código.", response = ItinerarioOut.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 404, message = "Itinerário não encontrado."),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarItinerario(@PathVariable final String codigo) {
        return ResponseEntity.ok(buscaItinerarioCodigoLinhaService.buscar(codigo));
    }

    @ApiOperation(value = "Deleta itinerário com id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deletado com sucesso."),
            @ApiResponse(code = 404, message = "Nenhum itinerário com id informado"),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @DeleteMapping("/{idItinerario}")
    public ResponseEntity<Void> deletarItinerario(@PathVariable final Long idItinerario) {
        deletaItinerarioService.deletar(idItinerario);

        return ResponseEntity.ok().build();
    }
}
