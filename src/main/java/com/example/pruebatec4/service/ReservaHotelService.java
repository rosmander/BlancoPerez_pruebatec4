package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.HotelDTO;
import com.example.pruebatec4.dto.HuespedDTO;
import com.example.pruebatec4.dto.ReservaHotelDTO;
import com.example.pruebatec4.model.Hotel;
import com.example.pruebatec4.model.ReservaHotel;
import com.example.pruebatec4.repository.ReservaHotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaHotelService implements IReservaHotelService {

    private final HotelService ihotelService;
    private final ReservaHotelRepository reservaHotelRepository;
    private final HuespedService huespedService;

    public ReservaHotelService(HotelService ihotelService,
                               ReservaHotelRepository reservaHotelRepository,
                               HuespedService huespedService) {
        this.ihotelService = ihotelService;
        this.reservaHotelRepository = reservaHotelRepository;
        this.huespedService = huespedService;
    }

    @Override
    public Double saveReservaHotel(ReservaHotelDTO reservaHotelDTO) {
        ReservaHotel reservaHotel = mapDtoToEntity(reservaHotelDTO);
        HotelDTO hotelDTO = ihotelService.findHotelById(reservaHotelDTO.getHotelId());

        if (hotelDTO.isReservado()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe una reserva con las mismas características"
            );
        }

        double totalAmount = hotelDTO.getPrecioPorNoche()
                * reservaHotelDTO.getCantidadNoches()
                * reservaHotelDTO.getNumeroHuespedes();

        Hotel hotel = ihotelService.mapDtoToEntity(hotelDTO);
        hotel.setId(reservaHotelDTO.getHotelId());
        reservaHotel.setHotel(hotel);

        ReservaHotel reservaHotelHuesped = reservaHotelRepository.save(reservaHotel);

        reservaHotel.getHuespedes().forEach(huespedRequest -> {
            huespedRequest.setReservaHotel(reservaHotelHuesped);
            HuespedDTO huesped = huespedService.mapEntityToDto(huespedRequest);
            huesped.setReservaHotelDTO(mapEntityToDto(reservaHotelHuesped));
            huesped.getReservaHotelDTO().setHotelId(reservaHotel.getId());
            huespedService.saveHuesped(huesped);
        });

        hotel.setReservado(true);
        ihotelService.updateHotel(reservaHotelDTO.getHotelId(),
                ihotelService.mapEntityToDto(hotel));

        return totalAmount;
    }

    @Override
    public ReservaHotelDTO findReservaHotelById(Long id) {
        ReservaHotel reservaHotel = reservaHotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró la reserva de hotel con ese ID."
                ));
        return mapEntityToDto(reservaHotel);
    }

    @Override
    public List<ReservaHotelDTO> findAllReservaHoteles() {
        List<ReservaHotel> reservaHoteles = reservaHotelRepository.findAll();

        if (reservaHoteles.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron reservas de hoteles."
            );
        }

        return reservaHoteles.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public String deleteReservaHotel(Long id) {
        List<ReservaHotel> reservas = reservaHotelRepository.findAll();

        List<ReservaHotel> reservasDelHotel = reservas.stream()
                .filter(reserva -> reserva.getHotel().getId().equals(id))
                .toList();

        if (reservasDelHotel.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron reservas para el hotel con ID: " + id
            );
        }

        Hotel hotel = reservasDelHotel.get(0).getHotel();
        reservaHotelRepository.deleteAll(reservasDelHotel);

        boolean tieneReservas = reservaHotelRepository.existsById(id);

        if (!tieneReservas) {
            hotel.setReservado(false);
            ihotelService.updateHotel(hotel.getId(), ihotelService.mapEntityToDto(hotel));
        }

        return "Se han eliminado las reservas del hotel con ID: " + id + " con éxito.";
    }

    @Override
    public String updateReservaHotel(Long id, ReservaHotelDTO reservaHotelDTO) {
        reservaHotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró la reserva de hotel con ID: " + id + ". No se pudo actualizar."
                ));

        ReservaHotel reservaHotel = mapDtoToEntity(reservaHotelDTO);
        reservaHotel.setId(id);
        reservaHotel.setCantidadNoches((int) ChronoUnit.DAYS.between(
                reservaHotelDTO.getFechaEntrada(), reservaHotelDTO.getFechaSalida()));
        reservaHotelRepository.save(reservaHotel);
        return "Se ha actualizado la reserva de hotel con éxito.";
    }

    public ReservaHotel mapDtoToEntity(ReservaHotelDTO reservaHotelDTO) {
        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setFechaEntrada(reservaHotelDTO.getFechaEntrada());
        reservaHotel.setFechaSalida(reservaHotelDTO.getFechaSalida());
        reservaHotel.setCantidadNoches(reservaHotelDTO.getCantidadNoches());
        reservaHotel.setUbicacion(reservaHotelDTO.getUbicacion());
        reservaHotel.setCodigoHotel(reservaHotelDTO.getCodigoHotel());
        reservaHotel.setNumeroHuespedes(reservaHotelDTO.getNumeroHuespedes());
        reservaHotel.setTipoHabitacion(reservaHotelDTO.getTipoHabitacion());
        reservaHotel.setHuespedes(reservaHotelDTO.getHuespedes().stream()
                .map(huespedService::mapDtoToEntity).toList());
        return reservaHotel;
    }

    public ReservaHotelDTO mapEntityToDto(ReservaHotel reservaHotel) {
        ReservaHotelDTO reservaHotelDTO = new ReservaHotelDTO();
        reservaHotelDTO.setFechaEntrada(reservaHotel.getFechaEntrada());
        reservaHotelDTO.setFechaSalida(reservaHotel.getFechaSalida());
        reservaHotelDTO.setCantidadNoches(reservaHotel.getCantidadNoches());
        reservaHotelDTO.setUbicacion(reservaHotel.getUbicacion());
        reservaHotelDTO.setCodigoHotel(reservaHotel.getCodigoHotel());
        reservaHotelDTO.setNumeroHuespedes(reservaHotel.getNumeroHuespedes());
        reservaHotelDTO.setTipoHabitacion(reservaHotel.getTipoHabitacion());
        reservaHotelDTO.setHuespedes(reservaHotel.getHuespedes().stream()
                .map(huespedService::mapEntityToDto).toList());
        return reservaHotelDTO;
    }
}