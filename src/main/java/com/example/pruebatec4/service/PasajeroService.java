package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.PasajeroDTO;
import com.example.pruebatec4.dto.ReservaVueloDTO;
import com.example.pruebatec4.model.Pasajero;
import com.example.pruebatec4.model.ReservaVuelo;
import com.example.pruebatec4.repository.PasajeroRepository;
import org.springframework.stereotype.Service;

@Service
public class PasajeroService implements IPasajeroService {

    private final PasajeroRepository pasajeroRepository;

    public PasajeroService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    @Override
    public void savePasajero(PasajeroDTO pasajeroDTO) {
        Pasajero pasajero = mapDtoToEntity(pasajeroDTO);
        pasajero.getReservaVuelo().setId(pasajeroDTO.getReservaVueloDTO().getVueloId());
        mapEntityToDto(pasajeroRepository.save(pasajero));
    }

    public PasajeroDTO mapEntityToDto(Pasajero pasajero){
        PasajeroDTO pasajeroDTO = new PasajeroDTO();
        pasajeroDTO.setReservaVueloDTO(new ReservaVueloDTO());
        pasajeroDTO.setNombre(pasajero.getNombre());
        pasajeroDTO.setApellido(pasajero.getApellido());
        pasajeroDTO.setEdad(pasajero.getEdad());
        return pasajeroDTO;
    }

    public Pasajero mapDtoToEntity(PasajeroDTO pasajeroDTO){
        Pasajero pasajero = new Pasajero();
        pasajero.setReservaVuelo(new ReservaVuelo());
        pasajero.setNombre(pasajeroDTO.getNombre());
        pasajero.setApellido(pasajeroDTO.getApellido());
        pasajero.setEdad(pasajeroDTO.getEdad());
        return pasajero;
    }
}