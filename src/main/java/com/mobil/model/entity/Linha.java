package com.mobil.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Linhas")
public class Linha {

    @Id
    @Column(name = "id_linha")
    private Long id;

    private String codigo;

    private String nome;

    @OneToMany(mappedBy = "linha", fetch = FetchType.LAZY)
    private List<Itinerario> itinerarios;
}
