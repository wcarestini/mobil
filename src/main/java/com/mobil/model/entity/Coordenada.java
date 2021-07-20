package com.mobil.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Coordenadas")
public class Coordenada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_coordenada")
    private Long id;

    @Column(columnDefinition = "decimal")
    private Double latitude;

    @Column(columnDefinition = "decimal")
    private Double longitude;

    @ManyToMany(mappedBy = "coordenadas")
    private List<Itinerario> itinerarios;
}
