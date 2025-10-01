package com.example.MicroService_Reserva.persistencia.repository;




import com.example.MicroService_Reserva.domain.RepositoryInterface.repositoryInterface;
import com.example.MicroService_Reserva.domain.dto.ReservaDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository implements repositoryInterface {


    private final List<ReservaDTO> baseDeDatos = new ArrayList<>();

    @Override
    public Iterable<ReservaDTO> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    @Override
    public Optional<ReservaDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ReservaDTO> findById(String id) {
        return baseDeDatos.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst();
    }

    @Override
    public ReservaDTO save(ReservaDTO reserva) {
        baseDeDatos.add(reserva);
        return reserva;
    }

    @Override
    public ReservaDTO update(ReservaDTO reserva) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(reserva.getId())) {
                baseDeDatos.set(i, reserva);
                return reserva;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public void delete(String id) {
        baseDeDatos.removeIf(reserva -> reserva.getId().equals(id));
    }

    @Override
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

    @Override
    public long count() {
        return baseDeDatos.size();
    }

    @Override
    public Optional<ReservaDTO> findByUsuario(String usuario) {
        return baseDeDatos.stream()
                .filter(reserva -> usuario.equals(reserva.getUsuario()))
                .findFirst();
    }

    @Override
    public Optional<ReservaDTO> findByVuelo(String vuelo) {
        return baseDeDatos.stream()
                .filter(reserva -> vuelo.equals(reserva.getVuelo()))
                .findFirst();
    }


}

