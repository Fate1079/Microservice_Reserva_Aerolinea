package com.example.MicroService_Reserva.service;


import com.example.MicroService_Reserva.dto.ReservaDTO;
import com.example.MicroService_Reserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                "Bogotá", "Medellín", "$350.000", "CONFIRMADA", "2025-09-10 08:00", "12A");

        ReservaDTO r2 = new ReservaDTO("R002", "Carlos Rodríguez", "LA456",
                "Medellín", "Miami", "$1.200.000", "PENDIENTE", "2025-09-15 14:00", "7C");

        ReservaDTO r3 = new ReservaDTO("R003", "Ana Martínez", "CM789",
                "Bogotá", "Panamá", "$850.000", "CANCELADA", "2025-09-20 09:30", "3B");

        save(r1);
        save(r2);
        save(r3);
    }

    // -------- CRUD --------
    public ReservaDTO save(ReservaDTO reserva) {
        return reservaRepository.save(reserva);
    }

    public ReservaDTO findById(String id) {
        return reservaRepository.findById(id);
    }

    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll();
    }

    public ReservaDTO update(ReservaDTO reserva) {
        return reservaRepository.update(reserva);
    }

    public void deleteById(String id) {
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