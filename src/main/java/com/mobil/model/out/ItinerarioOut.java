package com.mobil.model.out;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItinerarioOut {

    private LinhaOut linha;

    private String nome;

    private String codigo;

    private List<CoordenadaOut> coordenadas;
}
