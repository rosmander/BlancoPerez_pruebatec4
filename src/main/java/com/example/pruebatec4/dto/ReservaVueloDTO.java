package com.example.pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaVueloDTO {
    private Long vueloId;
    private LocalDate fechaSalida;
    private String origen;
    private String destino;
    private String numeroVuelo;
    private Integer numeroPersonas;
    private String tipoAsiento;
    private List<PasajeroDTO> pasajeros;
}