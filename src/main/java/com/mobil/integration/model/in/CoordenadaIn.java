package com.mobil.integration.model.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CoordenadaIn {

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("lng")
    private Double longitude;
}
