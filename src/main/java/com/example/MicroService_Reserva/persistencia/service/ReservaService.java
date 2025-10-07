package com.example.MicroService_Reserva.persistencia.service;

import com.example.MicroService_Reserva.domain.dto.ReservaDTO;
import com.example.MicroService_Reserva.persistencia.Entity.Reserva;
import com.example.MicroService_Reserva.persistencia.Excepciones.ReservaInvalidaException;
import com.example.MicroService_Reserva.persistencia.Excepciones.ReservaNoEncontradaException;
import com.example.MicroService_Reserva.persistencia.Excepciones.ReservaYaActivaException;
import com.example.MicroService_Reserva.persistencia.crudRepository.ReservaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaCrudRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaCrudRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva save(ReservaDTO reservaDTO) {
        if (reservaDTO.getUsuario() == null || reservaDTO.getUsuario().isBlank()) {
            throw new ReservaInvalidaException("La reserva debe tener un usuario válido");
        }
        if (reservaDTO.getVuelo() == null || reservaDTO.getVuelo().isBlank()) {
            throw new ReservaInvalidaException("La reserva debe estar asociada a un vuelo válido");
        }
        if (reservaDTO.getNumasiento() == null || reservaDTO.getNumasiento().isBlank()) {
            throw new ReservaInvalidaException("La reserva debe tener un número de asiento válido");
        }

        // Validar reserva activa
        List<Reserva> reservasUsuario = reservaRepository.findByUsuarioContaining(reservaDTO.getUsuario());
        for (Reserva r : reservasUsuario) {
            if (r.getVuelo().equals(reservaDTO.getVuelo()) &&
                    "CONFIRMADA".equalsIgnoreCase(r.getEstado())) {
                throw new ReservaYaActivaException("El usuario ya tiene una reserva confirmada en este vuelo.");
            }
        }

        // Mapear DTO a entidad sin cambiar estructura
        Reserva reserva = new Reserva();
        reserva.setUsuario(reservaDTO.getUsuario());
        reserva.setVuelo(reservaDTO.getVuelo());
        reserva.setEstado(reservaDTO.getEstado());
        reserva.setNumasiento(reservaDTO.getNumasiento());

        // Guardar y retornar
        return reservaRepository.save(reserva);
    }

    public Reserva findById(String id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("No se encontró la reserva con ID: " + id));
    }

    public List<Reserva> findAll() {
        return (List<Reserva>) reservaRepository.findAll();
    }

    public void deleteById(String id) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNoEncontradaException("No se puede eliminar, la reserva con ID: " + id + " no existe");
        }
        reservaRepository.deleteById(id);
    }

    // -------- Búsquedas --------
    public List<Reserva> buscarPorUsuario(String usuario) {
        return reservaRepository.findByUsuarioContaining(usuario);
    }

    public List<Reserva> buscarPorEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }

    public List<Reserva> buscarPorVuelo(String vuelo) {
        return reservaRepository.findByVuelo(vuelo);
    }

    // -------- Utilitarios --------
    public long count() {
        return reservaRepository.count();
    }

    public boolean existsById(String id) {
        return reservaRepository.existsById(id);
    }

    public long countByEstado(String estado) {
        return reservaRepository.countByEstado(estado);
    }

    //aa//
}