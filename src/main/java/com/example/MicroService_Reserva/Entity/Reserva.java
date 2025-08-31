package com.example.MicroService_Reserva.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String usuario;   // Nombre del pasajero o usuario

    @Column(nullable = false)
    private String vuelo;     // Código de vuelo (ej: AV123)

    @Column(nullable = false)
    private String origen;    // Ciudad de origen

    @Column(nullable = false)
    private String destino;   // Ciudad de destino

    @Column(nullable = false)
    private double precio;    // Precio del tiquete

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;  // PENDIENTE, CONFIRMADA, CANCELADA

    @Column(name = "fecha_vuelo", nullable = false)
    private LocalDateTime fechaVuelo; // Fecha y hora del vuelo

    @Column(name = "fecha_vuelo_Final", nullable = false)
    private LocalDateTime fechaVueloFinal;

    @Column(nullable = false)
    private String asiento;   // Número de asiento

    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = EstadoReserva.PENDIENTE;
        }
    }




    //aa
}