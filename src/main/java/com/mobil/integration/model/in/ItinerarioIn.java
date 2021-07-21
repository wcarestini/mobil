package com.mobil.integration.model.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ItinerarioIn {

    @JsonProperty("idlinha")
    private Long idLinha;

    private String nome;

    private String codigo;

    private List<CoordenadaIn> coordenadas;
}
