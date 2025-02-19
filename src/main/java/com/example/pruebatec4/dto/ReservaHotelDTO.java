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
public class ReservaHotelDTO {
    private Long hotelId;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Integer cantidadNoches;
    private String ubicacion;
    private String codigoHotel;
    private Integer numeroHuespedes;
    private String tipoHabitacion;
    private List<HuespedDTO> huespedes;
}