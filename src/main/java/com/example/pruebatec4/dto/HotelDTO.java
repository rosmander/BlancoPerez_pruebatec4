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
public class HotelDTO {
    private String codigoHotel;
    private String nombre;
    private String ubicacion;
    private String tipoHabitacion;
    private Double precioPorNoche;
    private LocalDate disponibleDesde;
    private LocalDate disponibleHasta;
    private boolean reservado;
}