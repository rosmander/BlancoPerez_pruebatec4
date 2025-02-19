package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.VueloDTO;

import java.util.List;

public interface IVueloService {
    List<VueloDTO> findAllVuelos();
    List<VueloDTO> findAvailableVuelos(String fechaSalida, String fechaLlegada,String origen, String destino);
    VueloDTO findVueloById(Long id);
    String saveVuelo(VueloDTO vueloDTO);
    String updateVuelo(Long id, VueloDTO vueloDTO);
    String deleteVuelo(Long id);
}