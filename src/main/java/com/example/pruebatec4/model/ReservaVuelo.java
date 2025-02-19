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
@Table(name = "reserva_vuelos")
public class ReservaVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaSalida;
    private String origen;
    private String destino;
    private String numeroVuelo;
    private Integer numeroPersonas;
    private String tipoAsiento;

    @OneToMany(mappedBy = "reservaVuelo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Pasajero> pasajeros;

    @ManyToOne
    private Vuelo vuelo;
}