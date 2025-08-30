package com.example.MicroService_Reserva.crudRepository;

import com.example.MicroService_Reserva.Entity.EstadoReserva;
import com.example.MicroService_Reserva.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Buscar reservas por usuario
    List<Reserva> findByUsuarioContaining(String usuario);

    // Buscar reservas por estado
    List<Reserva> findByEstado(EstadoReserva estado);

    // Buscar reservas por vuelo
    List<Reserva> findByAvion(String avion);

    // Buscar reservas por origen y destino
    List<Reserva> findByOrigenAndDestino(String origen, String destino);

    // Contar reservas por estado
    long countByEstado(EstadoReserva estado);
}