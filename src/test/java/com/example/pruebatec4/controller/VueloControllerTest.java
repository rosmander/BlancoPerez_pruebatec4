package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.VueloDTO;
import com.example.pruebatec4.service.VueloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VueloControllerTest {

    @Autowired
    private VueloService vueloService;

    @Test
    void testGetAllFlights() {
        List<VueloDTO> vuelos = vueloService.findAllVuelos();
        assertNotNull(vuelos);
        assertFalse(vuelos.isEmpty());
    }

    @Test
    void testGetAvailableFlights() {
        String dateFrom = "2000-02-10";
        String dateTo = "2050-02-15";
        String origin = "Barcelona";
        String destination = "Miami";

        List<VueloDTO> availableFlights = vueloService.findAvailableVuelos(dateFrom, dateTo, origin, destination);
        assertNotNull(availableFlights);
        assertFalse(availableFlights.isEmpty());
    }
}