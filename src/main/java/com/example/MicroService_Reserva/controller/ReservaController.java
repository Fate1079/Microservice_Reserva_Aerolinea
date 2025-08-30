package com.example.MicroService_Reserva.controller;


import com.example.MicroService_Reserva.dto.ReservaDTO;
import com.example.MicroService_Reserva.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "API para la gestión de reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las reservas", description = "Devuelve una lista de todas las reservas registradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una reserva por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<ReservaDTO> getReservaById(
            @PathVariable @Parameter(description = "ID de la reserva") String id) {
        ReservaDTO reserva = reservaService.findById(id);
        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Guardar una reserva personalizada")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ReservaDTO> guardarReserva(
            @RequestBody @Parameter(description = "Datos de la reserva a guardar") ReservaDTO reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.save(reserva));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<ReservaDTO> actualizarReserva(
            @PathVariable @Parameter(description = "ID de la reserva") String id,
            @RequestBody @Parameter(description = "Datos de la reserva actualizados") ReservaDTO reserva) {
        if (!reservaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reserva.setId(id);
        ReservaDTO actualizada = reservaService.update(reserva);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> eliminarReserva(
            @PathVariable @Parameter(description = "ID de la reserva") String id) {
        if (!reservaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/usuario")
    @Operation(summary = "Buscar reservas por usuario")
    public ResponseEntity<List<ReservaDTO>> buscarPorUsuario(
            @RequestParam @Parameter(description = "Nombre del usuario") String usuario) {
        return ResponseEntity.ok(reservaService.buscarPorUsuario(usuario));
    }

    @GetMapping("/buscar/estado")
    @Operation(summary = "Buscar reservas por estado")
    public ResponseEntity<List<ReservaDTO>> buscarPorEstado(
            @RequestParam @Parameter(description = "Estado de la reserva") String estado) {
        return ResponseEntity.ok(reservaService.buscarPorEstado(estado));
    }

    @GetMapping("/buscar/vuelo")
    @Operation(summary = "Buscar reservas por código de vuelo")
    public ResponseEntity<List<ReservaDTO>> buscarPorVuelo(
            @RequestParam @Parameter(description = "Código del vuelo") String vuelo) {
        return ResponseEntity.ok(reservaService.buscarPorVuelo(vuelo));
    }

    @GetMapping("/buscar/ruta")
    @Operation(summary = "Buscar reservas por origen y destino")
    public ResponseEntity<List<ReservaDTO>> buscarPorOrigenDestino(
            @RequestParam @Parameter(description = "Ciudad de origen") String origen,
            @RequestParam @Parameter(description = "Ciudad de destino") String destino) {
        return ResponseEntity.ok(reservaService.buscarPorOrigenDestino(origen, destino));
    }

    @GetMapping("/contar")
    @Operation(summary = "Contar total de reservas")
    public ResponseEntity<Long> contarReservas() {
        return ResponseEntity.ok(reservaService.count());
    }

    @GetMapping("/contar/estado")
    @Operation(summary = "Contar reservas por estado")
    public ResponseEntity<Long> contarPorEstado(
            @RequestParam @Parameter(description = "Estado de la reserva") String estado) {
        return ResponseEntity.ok(reservaService.countByEstado(estado));
    }
}
