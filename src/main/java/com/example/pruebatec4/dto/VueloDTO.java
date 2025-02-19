package com.example.pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {
    private String numeroVuelo;
    private String origen;
    private String destino;
    private String tipoAsiento;
    private Double precioPorPersona;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
}