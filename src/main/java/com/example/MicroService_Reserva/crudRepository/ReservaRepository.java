package com.example.MicroService_Reserva.crudRepository;

import com.example.MicroService_Reserva.Entity.EstadoReserva;
import com.example.MicroService_Reserva.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


    List<Reserva> findByUsuarioContaining(String usuario);


    List<Reserva> findByEstado(EstadoReserva estado);


    List<Reserva> findByAvion(String avion);


    List<Reserva> findByOrigenAndDestino(String origen, String destino);


    long countByEstado(EstadoReserva estado);
}