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
@Table(name = "hoteles")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoHotel;
    private String nombre;
    private String ubicacion;
    private String tipoHabitacion;
    private Double precioPorNoche;
    private LocalDate disponibleDesde;
    private LocalDate disponibleHasta;
    private boolean reservado;

    @OneToMany(mappedBy = "hotel")
    private List<ReservaHotel> reservas;
}