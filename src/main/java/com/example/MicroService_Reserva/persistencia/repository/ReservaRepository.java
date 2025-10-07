package com.example.MicroService_Reserva.persistencia.repository;




import com.example.MicroService_Reserva.domain.RepositoryInterface.repositoryInterface;
import com.example.MicroService_Reserva.domain.dto.ReservaDTO;
import com.example.MicroService_Reserva.persistencia.Entity.Reserva;
import com.example.MicroService_Reserva.persistencia.crudRepository.ReservaCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository implements repositoryInterface {

    @Autowired
    private ReservaCrudRepository reservaCrudRepository;
    

    @Override
    public List<ReservaDTO> findAll() {
        List<Reserva> reservas = reservaCrudRepository.findAll();
        List<ReservaDTO> dtoList = new ArrayList<>();
        for (Reserva r : reservas) {
            dtoList.add(toDTO(r));
        }
        return dtoList;
    }

    @Override
    public Optional<ReservaDTO> findById(String id) {
        return reservaCrudRepository.findById(id).map(this::toDTO);
    }

    @Override
    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva entity = toEntity(reservaDTO);
        Reserva saved = reservaCrudRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public ReservaDTO update(ReservaDTO reservaDTO) {
        if (reservaDTO.getId() == null || reservaDTO.getId().isBlank()) {
            throw new IllegalArgumentException("El id de la reserva no puede ser nulo ni vacío para actualizar");
        }
        if (!reservaCrudRepository.existsById(reservaDTO.getId())) {
            throw new IllegalArgumentException("No existe una reserva con el id especificado");
        }
        Reserva entity = toEntity(reservaDTO);
        Reserva updated = reservaCrudRepository.save(entity);
        return toDTO(updated);
    }

    @Override
    public void delete(String id) {
        reservaCrudRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return reservaCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return reservaCrudRepository.count();
    }

    @Override
    public Optional<ReservaDTO> findByUsuario(String usuario) {
        List<Reserva> reservas = reservaCrudRepository.findByUsuarioContaining(usuario);
        if (reservas == null || reservas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(reservas.get(0)));
    }

    @Override
    public Optional<ReservaDTO> findByVuelo(String vuelo) {
        List<Reserva> reservas = reservaCrudRepository.findByVuelo(vuelo);
        if (reservas == null || reservas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(reservas.get(0)));
    }




    // --- Métodos auxiliares de mapeo ---
    private ReservaDTO toDTO(Reserva r) {
        if (r == null) return null;
        ReservaDTO dto = new ReservaDTO();
        dto.setId(r.getId());
        dto.setUsuario(r.getUsuario());
        dto.setVuelo(r.getVuelo());
        dto.setEstado(r.getEstado());
        dto.setNumasiento(r.getNumasiento());
        return dto;
    }

    private Reserva toEntity(ReservaDTO d) {
        if (d == null) return null;
        Reserva r = new Reserva();
        r.setId(d.getId());
        r.setUsuario(d.getUsuario());
        r.setVuelo(d.getVuelo());
        r.setEstado(d.getEstado());
        r.setNumasiento(d.getNumasiento());
        return r;
    }


}

