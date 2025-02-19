package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.ReservaHotelDTO;

import java.util.List;

public interface IReservaHotelService {
    Double saveReservaHotel(ReservaHotelDTO reservaHotelDTO);
    ReservaHotelDTO findReservaHotelById(Long id);
    List<ReservaHotelDTO> findAllReservaHoteles();
    String deleteReservaHotel(Long id);
    String updateReservaHotel(Long id, ReservaHotelDTO reservaHotelDTO);
}