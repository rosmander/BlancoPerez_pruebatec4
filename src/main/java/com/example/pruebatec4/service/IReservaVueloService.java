package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.ReservaVueloDTO;

import java.util.List;

public interface IReservaVueloService {
    Double saveReservaVuelo(ReservaVueloDTO reservaVueloDTO);
    ReservaVueloDTO findReservaVueloById(Long id);
    List<ReservaVueloDTO> findAllReservaVuelos();
    String deleteReservaVuelo(Long id);
    String updateReservaVuelo(Long id, ReservaVueloDTO reservaVueloDTO);
}