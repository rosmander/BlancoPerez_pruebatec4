package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.HotelDTO;
import com.example.pruebatec4.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotelControllerTest {

    @Autowired
    private HotelService hotelService;

    @Test
    void testGetAllHotels() {
        List<HotelDTO> hoteles = hotelService.findAllHotels();
        assertNotNull(hoteles);
        assertFalse(hoteles.isEmpty());
    }

    @Test
    void testGetAvailableRooms() {
        String dateFrom = "2000-02-10";
        String dateTo = "2050-03-20";
        String place = "Miami";

        List<HotelDTO> availableRooms = hotelService.findAvailableRooms(dateFrom, dateTo, place);
        assertNotNull(availableRooms);
        assertFalse(availableRooms.isEmpty());
    }
}