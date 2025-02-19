package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.HuespedDTO;
import com.example.pruebatec4.dto.ReservaHotelDTO;
import com.example.pruebatec4.model.Huesped;
import com.example.pruebatec4.model.ReservaHotel;
import com.example.pruebatec4.repository.HuespedRepository;
import org.springframework.stereotype.Service;

@Service
public class HuespedService implements IHuespedService {

    private final HuespedRepository huespedRepository;

    public HuespedService(HuespedRepository huespedRepository) {
        this.huespedRepository = huespedRepository;
    }

    @Override
    public void saveHuesped(HuespedDTO huespedDTO) {
        Huesped huesped = mapDtoToEntity(huespedDTO);
        huesped.getReservaHotel().setId(huespedDTO.getReservaHotelDTO().getHotelId());
        mapEntityToDto(huespedRepository.save(huesped));
    }

    public HuespedDTO mapEntityToDto(Huesped huesped){
        HuespedDTO huespedDTO = new HuespedDTO();
        huespedDTO.setReservaHotelDTO(new ReservaHotelDTO());
        huespedDTO.setNombre(huesped.getNombre());
        huespedDTO.setApellido(huesped.getApellido());
        huespedDTO.setEdad(huesped.getEdad());
        return huespedDTO;
    }

    public Huesped mapDtoToEntity(HuespedDTO huespedDTO){
        Huesped huesped = new Huesped();
        huesped.setReservaHotel(new ReservaHotel());
        huesped.setNombre(huespedDTO.getNombre());
        huesped.setApellido(huespedDTO.getApellido());
        huesped.setEdad(huespedDTO.getEdad());
        return huesped;
    }
}