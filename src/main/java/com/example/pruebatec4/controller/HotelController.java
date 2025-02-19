package com.example.pruebatec4.controller;

import com.example.pruebatec4.dto.HotelDTO;
import com.example.pruebatec4.service.IHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class HotelController {

    private final IHotelService hotelService;

    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Obtener todos los hoteles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hoteles obtenido exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron hoteles")
    })
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hoteles = hotelService.findAllHotels();
        if (hoteles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hoteles, HttpStatus.OK);
    }

    @Operation(summary = "Obtener habitaciones disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de habitaciones disponibles obtenido exitosamente"),
            @ApiResponse(responseCode = "204", description = "Parámetros de solicitud inválidos")
    })
    @GetMapping("/rooms")
    public ResponseEntity<List<HotelDTO>> getAvailableRooms(
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam String place) {
        List<HotelDTO> availableRooms = hotelService.findAvailableRooms(dateFrom, dateTo, place);
        if (availableRooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un hotel por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado") // Cambiado a 404
    })
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        HotelDTO hotelDTO = hotelService.findHotelById(id);
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del hotel inválidos")
    })
    @PostMapping("/hotels/new")
    public ResponseEntity<String> createHotel(@RequestBody HotelDTO hotelDTO) {
        String mensaje = hotelService.saveHotel(hotelDTO);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un hotel existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })
    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        String mensaje = hotelService.updateHotel(id, hotelDTO);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un hotel por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })
    @DeleteMapping("/hotels/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        String mensaje = hotelService.deleteHotel(id);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }
}