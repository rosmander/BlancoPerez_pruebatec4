package com.example.pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HuespedDTO {
    private ReservaHotelDTO reservaHotelDTO;
    private String nombre;
    private String apellido;
    private Integer edad;
}