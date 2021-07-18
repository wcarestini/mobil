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
@Entity(name = "Paradas")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_parada")
    private Long id;

    @Column(columnDefinition = "decimal")
    private Double latitute;

    @Column(columnDefinition = "decimal")
    private Double longitude;

    @ManyToMany(mappedBy = "paradas")
    private List<Itinerario> itinerarios;
}
