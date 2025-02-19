package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.HotelDTO;

import java.util.List;

public interface IHotelService {
    List<HotelDTO> findAllHotels();
    List<HotelDTO> findAvailableRooms(String dateFrom, String dateTo, String ubicacion);
    HotelDTO findHotelById(Long id);
    String saveHotel(HotelDTO hotelDTO);
    String updateHotel(Long id, HotelDTO hotelDTO);
    String deleteHotel(Long id);
}