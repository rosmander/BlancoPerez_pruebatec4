package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.*;
import com.example.pruebatec4.model.*;
import com.example.pruebatec4.repository.ReservaVueloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservaVueloService implements IReservaVueloService {

    private static final String RESERVA_NO_ENCONTRADA = "No se encontró la reserva de vuelo con ID: ";

    private final VueloService ivueloService;
    private final ReservaVueloRepository reservaVueloRepository;
    private final PasajeroService pasajeroService;

    public ReservaVueloService(VueloService ivueloService,
                               ReservaVueloRepository reservaVueloRepository,
                               PasajeroService pasajeroService) {
        this.ivueloService = ivueloService;
        this.reservaVueloRepository = reservaVueloRepository;
        this.pasajeroService = pasajeroService;
    }

    @Override
    public Double saveReservaVuelo(ReservaVueloDTO reservaVueloDTO) {
        try {
            ReservaVuelo reservaVuelo = mapDtoToEntity(reservaVueloDTO);
            VueloDTO vueloDTO = ivueloService.findVueloById(reservaVueloDTO.getVueloId());

            double totalAmount = reservaVueloDTO.getNumeroPersonas()
                    * vueloDTO.getPrecioPorPersona();

            Vuelo vuelo = ivueloService.mapDtoToEntity(vueloDTO);
            vuelo.setId(reservaVueloDTO.getVueloId());
            reservaVuelo.setVuelo(vuelo);

            ReservaVuelo reservaVueloPasajero = reservaVueloRepository.save(reservaVuelo);

            reservaVuelo.getPasajeros().forEach(pasajeroRequest -> {
                pasajeroRequest.setReservaVuelo(reservaVueloPasajero);
                PasajeroDTO pasajero = pasajeroService.mapEntityToDto(pasajeroRequest);
                pasajero.setReservaVueloDTO(mapEntityToDto(reservaVueloPasajero));
                pasajero.getReservaVueloDTO().setVueloId(reservaVuelo.getId());
                pasajeroService.savePasajero(pasajero);
            });

            ivueloService.updateVuelo(reservaVueloDTO.getVueloId(),
                    ivueloService.mapEntityToDto(vuelo));

            return totalAmount;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Datos de la reserva inválidos"
            );
        }
    }

    @Override
    public ReservaVueloDTO findReservaVueloById(Long id) {
        ReservaVuelo reservaVuelo = reservaVueloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        RESERVA_NO_ENCONTRADA + id
                ));
        return mapEntityToDto(reservaVuelo);
    }

    @Override
    public List<ReservaVueloDTO> findAllReservaVuelos() {
        List<ReservaVuelo> reservaVuelos = reservaVueloRepository.findAll();

        if (reservaVuelos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron reservas de vuelo."
            );
        }

        return reservaVuelos.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public String deleteReservaVuelo(Long id) {
        reservaVueloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        RESERVA_NO_ENCONTRADA + id + ". No se pudo eliminar."
                ));

        reservaVueloRepository.deleteById(id);
        return "Se ha eliminado la reserva de vuelo con éxito.";
    }

    @Override
    public String updateReservaVuelo(Long id, ReservaVueloDTO reservaVueloDTO) {
        reservaVueloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        RESERVA_NO_ENCONTRADA + id + ". No se pudo actualizar."
                ));

        ReservaVuelo reservaActualizada = mapDtoToEntity(reservaVueloDTO);
        reservaActualizada.setId(id);
        reservaVueloRepository.save(reservaActualizada);
        return "Se ha actualizado la reserva de vuelo con éxito.";
    }

    public ReservaVuelo mapDtoToEntity(ReservaVueloDTO reservaVueloDTO) {
        ReservaVuelo reservaVuelo = new ReservaVuelo();
        reservaVuelo.setFechaSalida(reservaVueloDTO.getFechaSalida());
        reservaVuelo.setOrigen(reservaVueloDTO.getOrigen());
        reservaVuelo.setDestino(reservaVueloDTO.getDestino());
        reservaVuelo.setNumeroVuelo(reservaVueloDTO.getNumeroVuelo());
        reservaVuelo.setNumeroPersonas(reservaVueloDTO.getNumeroPersonas());
        reservaVuelo.setTipoAsiento(reservaVueloDTO.getTipoAsiento());
        reservaVuelo.setPasajeros(reservaVueloDTO.getPasajeros().stream()
                .map(pasajeroService::mapDtoToEntity).toList());
        return reservaVuelo;
    }

    public ReservaVueloDTO mapEntityToDto(ReservaVuelo reservaVuelo) {
        ReservaVueloDTO reservaVueloDTO = new ReservaVueloDTO();
        reservaVueloDTO.setFechaSalida(reservaVuelo.getFechaSalida());
        reservaVueloDTO.setOrigen(reservaVuelo.getOrigen());
        reservaVueloDTO.setDestino(reservaVuelo.getDestino());
        reservaVueloDTO.setNumeroVuelo(reservaVuelo.getNumeroVuelo());
        reservaVueloDTO.setNumeroPersonas(reservaVuelo.getNumeroPersonas());
        reservaVueloDTO.setTipoAsiento(reservaVuelo.getTipoAsiento());
        reservaVueloDTO.setPasajeros(reservaVuelo.getPasajeros().stream()
                .map(pasajeroService::mapEntityToDto).toList());
        return reservaVueloDTO;
    }
}