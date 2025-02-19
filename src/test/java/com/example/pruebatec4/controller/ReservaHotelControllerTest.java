package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.ReservaHotelDTO;
import com.example.pruebatec4.service.ReservaHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservaHotelControllerTest {

    @Autowired
    private ReservaHotelService reservaHotelService;

    @Test
    void testBookRoom() {
        ReservaHotelDTO reservaHotelDTO = new ReservaHotelDTO();
        reservaHotelDTO.setHotelId(1L);
        reservaHotelDTO.setFechaEntrada(LocalDate.of(2024, 2, 10));
        reservaHotelDTO.setFechaSalida(LocalDate.of(2024, 2, 15));
        reservaHotelDTO.setCantidadNoches(5);
        reservaHotelDTO.setUbicacion("Miami");
        reservaHotelDTO.setCodigoHotel("AR-0002");
        reservaHotelDTO.setNumeroHuespedes(2);
        reservaHotelDTO.setTipoHabitacion("Doble");
        reservaHotelDTO.setHuespedes(Collections.emptyList());

        Double totalAmount = reservaHotelService.saveReservaHotel(reservaHotelDTO);
        assertNotNull(totalAmount);
        assertTrue(totalAmount > 0);
    }
}