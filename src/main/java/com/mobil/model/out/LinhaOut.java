package com.mobil.model.out;

import com.mobil.model.entity.Itinerario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LinhaOut {

    private String codigo;

    private String nome;

    private List<Itinerario> itinerarios;
}
