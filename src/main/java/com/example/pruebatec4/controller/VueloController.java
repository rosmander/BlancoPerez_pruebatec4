package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.VueloDTO;
import com.example.pruebatec4.service.IVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class VueloController {

    private final IVueloService vueloService;

    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }

    @Operation(summary = "Obtener vuelos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado de vuelos obtenido exitosamente"),
            @ApiResponse(responseCode = "400",
                    description = "La fecha de salida no puede ser posterior a la fecha de llegada"),
            @ApiResponse(responseCode = "404",
                    description = "No se encontraron vuelos")
    })
    @GetMapping("/flights")
    public ResponseEntity<List<VueloDTO>> getFlights(
            @RequestParam(value = "fechaSalida", required = false) String fechaSalida,
            @RequestParam(value = "fechaLlegada", required = false) String fechaLlegada,
            @RequestParam(value = "origen", required = false) String origen,
            @RequestParam(value = "destino", required = false) String destino
    ) {
        List<VueloDTO> vuelos;

        if (fechaSalida != null || fechaLlegada != null || origen != null || destino != null) {
            if (!(fechaSalida != null && fechaLlegada != null && origen != null && destino != null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vuelos = vueloService.findAvailableVuelos(fechaSalida, fechaLlegada, origen, destino);
        } else {
            vuelos = vueloService.findAllVuelos();
        }

        return new ResponseEntity<>(vuelos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un vuelo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vuelo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @GetMapping("/flights/{id}")
    public ResponseEntity<VueloDTO> getFlightById(@PathVariable Long id) {
        VueloDTO vueloDTO = vueloService.findVueloById(id);
        return new ResponseEntity<>(vueloDTO, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vuelo creado"),
            @ApiResponse(responseCode = "400", description = "Datos del vuelo inválidos")
    })
    @PostMapping("/flights/new")
    public ResponseEntity<String> createFlight(@RequestBody VueloDTO vueloDTO) {
        String mensaje = vueloService.saveVuelo(vueloDTO);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un vuelo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vuelo actualizado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @PutMapping("/flights/edit/{id}")
    public ResponseEntity<String> updateFlight(@PathVariable Long id, @RequestBody VueloDTO vueloDTO) {
        String mensaje = vueloService.updateVuelo(id, vueloDTO);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un vuelo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vuelo eliminado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @DeleteMapping("/flights/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        String mensaje = vueloService.deleteVuelo(id);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }
}