package com.example.MicroService_Reserva.service;



import com.example.MicroService_Reserva.Excepciones.ReservaInvalidaException;
import com.example.MicroService_Reserva.Excepciones.ReservaNoEncontradaException;
import com.example.MicroService_Reserva.Excepciones.ReservaYaActivaException;
import com.example.MicroService_Reserva.dto.ReservaDTO;

import com.example.MicroService_Reserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
        initSampleData();
    }

    // -------- Inicializar datos simulados --------
    private void initSampleData() {
        ReservaDTO r1 = new ReservaDTO("R001", "María González", "AV123",
                "Bogotá", "Medellín", "$350.000", "CONFIRMADA",
                LocalDate.of(2025, 9, 10), LocalDate.of(2025, 10, 15), "12A");

        ReservaDTO r2 = new ReservaDTO("R002", "Carlos Rodríguez", "LA456",
                "Medellín", "Miami", "$1.200.000", "PENDIENTE",
                LocalDate.of(2025, 9, 15), LocalDate.of(2025, 10, 15), "7C");

        ReservaDTO r3 = new ReservaDTO("R003", "Ana Martínez", "CM789",
                "Bogotá", "Panamá", "$850.000", "CANCELADA",
                LocalDate.of(2025, 9, 20), LocalDate.of(2025, 10, 15), "3B");

        save(r1);
        save(r2);
        save(r3);
    }

    // -------- CRUD --------
    public ReservaDTO save(ReservaDTO reserva) {
        // Validar si ya existe una reserva activa para el mismo usuario y vuelo
        List<ReservaDTO> reservasUsuario = reservaRepository.findByUsuario(reserva.getUsuario());
        for (int i = 0; i < reservasUsuario.size(); i++) {
            ReservaDTO r = reservasUsuario.get(i);

            if (r.getVuelo().equals(reserva.getVuelo()) &&
                    r.getEstado().equalsIgnoreCase("ACTIVA") &&
                    r.getFechaVueloFinal().isAfter(reserva.getFechaVuelo())) {

                throw new ReservaYaActivaException(
                        "El usuario ya tiene una reserva activa en este vuelo hasta " + r.getFechaVueloFinal()
                );
            }
        }

        // Validación de datos mínimos
        if (reserva.getUsuario() == null || reserva.getUsuario().isBlank()) {
            throw new ReservaInvalidaException("La reserva debe tener un usuario válido");
        }
        if (reserva.getVuelo() == null || reserva.getVuelo().isBlank()) {
            throw new ReservaInvalidaException("La reserva debe estar asociada a un vuelo válido");
        }
        if (reserva.getFechaVuelo() == null || reserva.getFechaVueloFinal() == null) {
            throw new ReservaInvalidaException("La reserva debe tener fechas de vuelo válidas");
        }
        if (reserva.getFechaVueloFinal().isBefore(reserva.getFechaVuelo())) {
            throw new ReservaInvalidaException("La fecha final del vuelo no puede ser antes de la fecha inicial");
        }

        return reservaRepository.save(reserva);
    }


    public ReservaDTO findById(String id) {
        ReservaDTO reserva = reservaRepository.findById(id);
        if (reserva == null) {
            throw new ReservaNoEncontradaException("No se encontró la reserva con ID: " + id);
        }
        return reserva;
    }

    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll();
    }

    public ReservaDTO update(ReservaDTO reserva) {
        if (!reservaRepository.existsById(reserva.getId())) {
            throw new ReservaNoEncontradaException("No existe la reserva con ID: " + reserva.getId());
        }
        return reservaRepository.update(reserva);
    }

    public void deleteById(String id) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNoEncontradaException("No se puede eliminar, la reserva con ID: " + id + " no existe");
        }
        reservaRepository.deleteById(id);
    }

    // -------- Búsquedas --------
    public List<ReservaDTO> buscarPorUsuario(String usuario) {
        return reservaRepository.findByUsuario(usuario);
    }

    public List<ReservaDTO> buscarPorEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }

    public List<ReservaDTO> buscarPorVuelo(String vuelo) {
        return reservaRepository.findByVuelo(vuelo);
    }

    public List<ReservaDTO> buscarPorOrigenDestino(String origen, String destino) {
        return reservaRepository.findByOrigenDestino(origen, destino);
    }

    // -------- Utilitarios --------
    public long count() {
        return reservaRepository.count();
    }

    public long countByEstado(String estado) {
        return reservaRepository.countByEstado(estado);
    }

    public boolean existsById(String id) {
        return reservaRepository.existsById(id);
    }

}

//aaaa