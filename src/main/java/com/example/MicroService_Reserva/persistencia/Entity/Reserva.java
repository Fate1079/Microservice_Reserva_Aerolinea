package com.example.MicroService_Reserva.persistencia.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;   // identificador generado como UUID

    @PrePersist
    private void prePersist() {
        if (this.id == null || this.id.isBlank()) {
            this.id = java.util.UUID.randomUUID().toString();
        }
    }

    @Column(nullable = false)
    private String usuario;   // nombre del usuario

    @Column(nullable = false)
    private String vuelo;     // código de vuelo

    @Column(nullable = false)
    private String estado;    // estado de la reserva

    @Column(name = "num_asiento", nullable = false)
    private String Numasiento; // número de asiento




    //aa
}