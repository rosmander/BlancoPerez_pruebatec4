package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.ReservaVueloDTO;
import com.example.pruebatec4.service.IReservaVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class ReservaVueloController {

    private final IReservaVueloService reservaVueloService;

    @Autowired
    public ReservaVueloController(IReservaVueloService reservaVueloService) {
        this.reservaVueloService = reservaVueloService;
    }

    @Operation(summary = "Crear una nueva reserva de vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada"),
            @ApiResponse(responseCode = "400", description = "Datos de la reserva inválidos")
    })
    @PostMapping("/flight-booking/new")
    public ResponseEntity<Double> bookFlight(@RequestBody ReservaVueloDTO reservaVueloDTO) {
        Double totalAmount = reservaVueloService.saveReservaVuelo(reservaVueloDTO);
        return new ResponseEntity<>(totalAmount, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una reserva de vuelo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @GetMapping("/flight-booking/{id}")
    public ResponseEntity<ReservaVueloDTO> getVueloById(@PathVariable Long id) {
        ReservaVueloDTO reservaVueloDTO = reservaVueloService.findReservaVueloById(id);
        return new ResponseEntity<>(reservaVueloDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las reservas de vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas"),
            @ApiResponse(responseCode = "204", description = "No se encontraron reservas")
    })
    @GetMapping("/flight-booking/all")
    public ResponseEntity<List<ReservaVueloDTO>> getAllReservaVuelos() {
        List<ReservaVueloDTO> reservaVuelos = reservaVueloService.findAllReservaVuelos();
        return new ResponseEntity<>(reservaVuelos, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una reserva de vuelo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @DeleteMapping("/flight-booking/delete/{id}")
    public ResponseEntity<String> deleteReservaVuelo(@PathVariable Long id) {
        String mensaje = reservaVueloService.deleteReservaVuelo(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una reserva de vuelo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva actualizada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @PutMapping("/flight-booking/edit/{id}")
    public ResponseEntity<String> updateReservaVuelo(@PathVariable Long id,
                                                     @RequestBody ReservaVueloDTO reservaVueloDTO) {
        String mensaje = reservaVueloService.updateReservaVuelo(id, reservaVueloDTO);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }
}