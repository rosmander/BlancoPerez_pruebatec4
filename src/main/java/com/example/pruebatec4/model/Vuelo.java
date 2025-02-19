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
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroVuelo;
    private String origen;
    private String destino;
    private String tipoAsiento;
    private Double precioPorPersona;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;

    @OneToMany(mappedBy = "vuelo")
    private List<ReservaVuelo> reservas;
}