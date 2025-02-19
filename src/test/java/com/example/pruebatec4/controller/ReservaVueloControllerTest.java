package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.ReservaVueloDTO;
import com.example.pruebatec4.service.ReservaVueloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservaVueloControllerTest {

    @Autowired
    private ReservaVueloService reservaVueloService;

    @Test
    void testBookFlight() {
        ReservaVueloDTO reservaVueloDTO = new ReservaVueloDTO();
        reservaVueloDTO.setVueloId(1L);
        reservaVueloDTO.setFechaSalida(LocalDate.of(2024, 2, 10));
        reservaVueloDTO.setOrigen("Barcelona");
        reservaVueloDTO.setDestino("Miami");
        reservaVueloDTO.setNumeroVuelo("BAMI-1235");
        reservaVueloDTO.setNumeroPersonas(2);
        reservaVueloDTO.setTipoAsiento("Economy");
        reservaVueloDTO.setPasajeros(Collections.emptyList());

        Double totalAmount = reservaVueloService.saveReservaVuelo(reservaVueloDTO);
        assertNotNull(totalAmount);
        assertTrue(totalAmount > 0);
    }
}