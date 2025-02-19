package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.ReservaHotelDTO;
import com.example.pruebatec4.service.IReservaHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class ReservaHotelController {

    private final IReservaHotelService reservaHotelService;

    public ReservaHotelController(IReservaHotelService reservaHotelService) {
        this.reservaHotelService = reservaHotelService;
    }

    @Operation(summary = "Crear una nueva reserva de hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada"),
            @ApiResponse(responseCode = "400", description = "Datos de la reserva inválidos")
    })
    @PostMapping("/room-booking/new")
    public ResponseEntity<Double> bookRoom(@RequestBody ReservaHotelDTO reservaHotelDTO) {
        Double totalAmount = reservaHotelService.saveReservaHotel(reservaHotelDTO);
        return new ResponseEntity<>(totalAmount, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una reserva de hotel por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @GetMapping("/room-booking/{id}")
    public ResponseEntity<ReservaHotelDTO> getReservaHotelById(@PathVariable Long id) {
        ReservaHotelDTO reservaHotelDTO = reservaHotelService.findReservaHotelById(id);
        return new ResponseEntity<>(reservaHotelDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las reservas de hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron reservas")
    })
    @GetMapping("/room-booking/all")
    public ResponseEntity<List<ReservaHotelDTO>> getAllReservaHoteles() {
        List<ReservaHotelDTO> reservaHotelDTO = reservaHotelService.findAllReservaHoteles();
        return new ResponseEntity<>(reservaHotelDTO, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar reservas de hotel por hotelId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas eliminadas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron reservas para el hotel")
    })
    @DeleteMapping("/room-booking/delete/{id}")
    public ResponseEntity<String> deleteReservaHotelById(@PathVariable Long id) {
        String mensaje = reservaHotelService.deleteReservaHotel(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una reserva de hotel existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @PutMapping("/room-booking/edit/{id}")
    public ResponseEntity<String> updateReservaHotel(@PathVariable Long id, @RequestBody ReservaHotelDTO reservaHotelDTO) {
        String mensaje = reservaHotelService.updateReservaHotel(id, reservaHotelDTO);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}