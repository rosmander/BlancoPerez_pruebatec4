package com.example.pruebatec4.service;

import com.example.pruebatec4.dto.VueloDTO;
import com.example.pruebatec4.model.Vuelo;
import com.example.pruebatec4.repository.VueloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VueloService implements IVueloService {

    private static final String VUELO_NO_ENCONTRADO = "No se encontró el vuelo con ID: ";

    private final VueloRepository vueloRepository;

    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public List<VueloDTO> findAvailableVuelos(String fechaSalidaStr, String fechaLlegadaStr,
                                              String origen, String destino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaLlegada = LocalDate.parse(fechaSalidaStr, formatter);
        LocalDate fechaSalida = LocalDate.parse(fechaLlegadaStr, formatter);

        if (fechaSalida.isBefore(fechaLlegada)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de salida no puede ser posterior a la fecha de llegada");
        }

        List<Vuelo> vuelosDisponibles = vueloRepository.findAll().stream()
                .filter(vuelo -> vuelo.getOrigen().equalsIgnoreCase(origen)
                        && vuelo.getDestino().equalsIgnoreCase(destino)
                        && vuelo.getFechaLlegada().isAfter(fechaLlegada)
                        && vuelo.getFechaSalida().isBefore(fechaSalida))
                .toList();

        if (vuelosDisponibles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron vuelos");
        }

        return vuelosDisponibles.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public List<VueloDTO> findAllVuelos() {
        List<Vuelo> vuelos = vueloRepository.findAll();

        if (vuelos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron vuelos");
        }

        return vuelos.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public VueloDTO findVueloById(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        VUELO_NO_ENCONTRADO + id));
        return mapEntityToDto(vuelo);
    }

    @Override
    public String saveVuelo(VueloDTO vueloDTO) {
        Vuelo vuelo = mapDtoToEntity(vueloDTO);

        try {
            vueloRepository.save(vuelo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un vuelo con el mismo número");
        }

        return "Se ha guardado el vuelo con éxito.";
    }

    @Override
    public String updateVuelo(Long id, VueloDTO vueloDTO) {
        vueloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        VUELO_NO_ENCONTRADO + id + ". No se pudo actualizar."));

        Vuelo vueloActualizado = mapDtoToEntity(vueloDTO);
        vueloActualizado.setId(id);
        vueloRepository.save(vueloActualizado);
        return "Se ha actualizado el vuelo con éxito.";
    }

    @Override
    public String deleteVuelo(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        VUELO_NO_ENCONTRADO + id + ". No se pudo eliminar."));

        if (!vuelo.getReservas().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede eliminar el vuelo porque está asociado a una reserva.");
        }

        vueloRepository.deleteById(id);
        return "El vuelo se eliminó con éxito.";
    }

    public Vuelo mapDtoToEntity(VueloDTO vueloDTO) {
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo(vueloDTO.getNumeroVuelo());
        vuelo.setOrigen(vueloDTO.getOrigen());
        vuelo.setDestino(vueloDTO.getDestino());
        vuelo.setTipoAsiento(vueloDTO.getTipoAsiento());
        vuelo.setPrecioPorPersona(vueloDTO.getPrecioPorPersona());
        vuelo.setFechaSalida(vueloDTO.getFechaSalida());
        vuelo.setFechaLlegada(vueloDTO.getFechaLlegada());
        return vuelo;
    }

    public VueloDTO mapEntityToDto(Vuelo vuelo) {
        VueloDTO vueloDTO = new VueloDTO();
        vueloDTO.setNumeroVuelo(vuelo.getNumeroVuelo());
        vueloDTO.setOrigen(vuelo.getOrigen());
        vueloDTO.setDestino(vuelo.getDestino());
        vueloDTO.setTipoAsiento(vuelo.getTipoAsiento());
        vueloDTO.setPrecioPorPersona(vuelo.getPrecioPorPersona());
        vueloDTO.setFechaSalida(vuelo.getFechaSalida());
        vueloDTO.setFechaLlegada(vuelo.getFechaLlegada());
        return vueloDTO;
    }
}