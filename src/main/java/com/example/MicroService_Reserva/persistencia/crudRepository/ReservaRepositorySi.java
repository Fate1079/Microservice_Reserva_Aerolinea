package com.example.MicroService_Reserva.persistencia.crudRepository;

import com.example.MicroService_Reserva.persistencia.Entity.EstadoReserva;
import com.example.MicroService_Reserva.persistencia.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositorySi extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuarioContaining(String usuario);

    List<Reserva> findByEstado(EstadoReserva estado);

    List<Reserva> findByVuelo(String vuelo);





    long countByEstado(EstadoReserva estado);


}
