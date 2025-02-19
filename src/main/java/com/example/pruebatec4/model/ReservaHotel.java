package com.example.pruebatec4.model;

import jakarta.persistence.*;
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
@Entity
public class ReservaHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Integer cantidadNoches;
    private String ubicacion;
    private String codigoHotel;
    private Integer numeroHuespedes;
    private String tipoHabitacion;

    @OneToMany(mappedBy = "reservaHotel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Huesped> huespedes;

    @ManyToOne
    private Hotel hotel;
}