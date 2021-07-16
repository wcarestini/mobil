package com.mobil.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Itinerarios")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_itinerario")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Linha linha;

    private String nome;

    private String codigo;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "itinerario_parada",
            joinColumns = @JoinColumn(name = "id_itinerario"),
            inverseJoinColumns = @JoinColumn(name = "id_parada")
    )
    private List<Parada> paradas;
}
