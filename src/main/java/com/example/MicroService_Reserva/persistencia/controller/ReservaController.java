package com.example.MicroService_Reserva.persistencia.controller;

import com.example.MicroService_Reserva.domain.dto.ReservaDTO;
import com.example.MicroService_Reserva.persistencia.Entity.Reserva;
import com.example.MicroService_Reserva.persistencia.service.ReservaService;
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

    // ----------- CRUD PRINCIPAL -----------

    @GetMapping
    @Operation(summary = "Obtener todas las reservas", description = "Devuelve una lista de todas las reservas registradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Reserva>> getAllReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una reserva por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Reserva> getReservaById(
            @PathVariable @Parameter(description = "ID de la reserva") String id) {
        Reserva reserva = reservaService.findById(id);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    @Operation(summary = "Guardar una nueva reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Reserva> guardarReserva(
            @RequestBody @Parameter(description = "Datos de la reserva a guardar") ReservaDTO reserva) {
        Reserva creada = reservaService.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> eliminarReserva(
            @PathVariable @Parameter(description = "ID de la reserva") String id) {
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ----------- BÚSQUEDAS -----------

    @GetMapping("/buscar/usuario")
    @Operation(summary = "Buscar reservas por usuario")
    public ResponseEntity<List<Reserva>> buscarPorUsuario(
            @RequestParam @Parameter(description = "Nombre del usuario") String usuario) {
        return ResponseEntity.ok(reservaService.buscarPorUsuario(usuario));
    }

    @GetMapping("/buscar/estado")
    @Operation(summary = "Buscar reservas por estado")
    public ResponseEntity<List<Reserva>> buscarPorEstado(
            @RequestParam @Parameter(description = "Estado de la reserva") String estado) {
        return ResponseEntity.ok(reservaService.buscarPorEstado(estado));
    }

    @GetMapping("/buscar/vuelo")
    @Operation(summary = "Buscar reservas por código de vuelo")
    public ResponseEntity<List<Reserva>> buscarPorVuelo(
            @RequestParam @Parameter(description = "Código del vuelo") String vuelo) {
        return ResponseEntity.ok(reservaService.buscarPorVuelo(vuelo));
    }

    // ----------- ESTADÍSTICAS -----------

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
