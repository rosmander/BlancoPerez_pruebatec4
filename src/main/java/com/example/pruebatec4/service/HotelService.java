package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.HotelDTO;
import com.example.pruebatec4.model.Hotel;
import com.example.pruebatec4.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    private static final String HOTEL_NO_ENCONTRADO = "Hotel no encontrado";

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> findAllHotels() {
        List<Hotel> hoteles = hotelRepository.findAll();
        if (hoteles.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "No se encontraron hoteles"
            );
        }
        return hoteles.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public List<HotelDTO> findAvailableRooms(
            String disponibleDesdeStr,
            String disponibleHastaStr,
            String place) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate disponibleDesde = LocalDate.parse(disponibleDesdeStr, formatter);
        LocalDate disponibleHasta = LocalDate.parse(disponibleHastaStr, formatter);

        List<Hotel> hotelesDisponibles = hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getUbicacion().equalsIgnoreCase(place)
                        && hotel.getDisponibleDesde().isAfter(disponibleDesde)
                        && hotel.getDisponibleHasta().isBefore(disponibleHasta))
                .toList();

        if (hotelesDisponibles.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "No hay habitaciones disponibles"
            );
        }

        return hotelesDisponibles.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public HotelDTO findHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró el hotel con ID: " + id
                ));
        return mapEntityToDto(hotel);
    }

    @Override
    public String saveHotel(HotelDTO hotelDTO) {
        Hotel hotel = mapDtoToEntity(hotelDTO);
        try {
            hotelRepository.save(hotel);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Los datos introducidos no son válidos"
            );
        }
        return "Se ha guardado el hotel con éxito.";
    }

    @Override
    public String updateHotel(Long id, HotelDTO hotelDTO) {
        hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HOTEL_NO_ENCONTRADO
                ));

        Hotel hotel = mapDtoToEntity(hotelDTO);
        hotel.setId(id);
        hotelRepository.save(hotel);
        return "Se ha actualizado el hotel con éxito.";
    }

    @Override
    public String deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HOTEL_NO_ENCONTRADO
                ));

        if (!hotel.getReservas().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede eliminar el hotel por tener una reserva asociada"
            );
        }

        hotel.setReservado(false);
        hotelRepository.save(hotel);
        hotelRepository.deleteById(id);
        return "El hotel se eliminó con éxito.";
    }

    public Hotel mapDtoToEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setCodigoHotel(hotelDTO.getCodigoHotel());
        hotel.setNombre(hotelDTO.getNombre());
        hotel.setUbicacion(hotelDTO.getUbicacion());
        hotel.setTipoHabitacion(hotelDTO.getTipoHabitacion());
        hotel.setPrecioPorNoche(hotelDTO.getPrecioPorNoche());
        hotel.setDisponibleDesde(hotelDTO.getDisponibleDesde());
        hotel.setDisponibleHasta(hotelDTO.getDisponibleHasta());
        hotel.setReservado(hotelDTO.isReservado());
        return hotel;
    }

    public HotelDTO mapEntityToDto(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setCodigoHotel(hotel.getCodigoHotel());
        hotelDTO.setNombre(hotel.getNombre());
        hotelDTO.setUbicacion(hotel.getUbicacion());
        hotelDTO.setTipoHabitacion(hotel.getTipoHabitacion());
        hotelDTO.setPrecioPorNoche(hotel.getPrecioPorNoche());
        hotelDTO.setDisponibleDesde(hotel.getDisponibleDesde());
        hotelDTO.setDisponibleHasta(hotel.getDisponibleHasta());
        hotelDTO.setReservado(hotel.isReservado());
        return hotelDTO;
    }
}