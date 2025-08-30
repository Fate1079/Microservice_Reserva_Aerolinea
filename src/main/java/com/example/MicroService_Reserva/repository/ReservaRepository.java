package com.example.MicroService_Reserva.repository;


import com.example.MicroService_Reserva.dto.ReservaDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaRepository {

    private final List<ReservaDTO> baseDeDatos = new ArrayList<>();

    // Crear o guardar una reserva
    public ReservaDTO save(ReservaDTO reserva) {
        baseDeDatos.add(reserva);
        return reserva;
    }

    // Buscar por ID
    public ReservaDTO findById(String id) {
        for (ReservaDTO reserva : baseDeDatos) {
            if (reserva.getId().equals(id)) {
                return reserva;
            }
        }
        return null;
    }

    // Obtener todas
    public List<ReservaDTO> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    // Eliminar por ID
    public void deleteById(String id) {
        baseDeDatos.removeIf(reserva -> reserva.getId().equals(id));
    }

    // Actualizar reserva
    public ReservaDTO update(ReservaDTO reserva) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(reserva.getId())) {
                baseDeDatos.set(i, reserva);
                return reserva;
            }
        }
        return null;
    }

    // ----------- Métodos específicos ------------

    public List<ReservaDTO> findByUsuario(String usuario) {
        List<ReservaDTO> resultado = new ArrayList<>();
        for (ReservaDTO reserva : baseDeDatos) {
            if (reserva.getUsuario() != null && reserva.getUsuario().contains(usuario)) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public List<ReservaDTO> findByEstado(String estado) {
        List<ReservaDTO> resultado = new ArrayList<>();
        for (ReservaDTO reserva : baseDeDatos) {
            if (estado.equals(reserva.getEstado())) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public List<ReservaDTO> findByVuelo(String vuelo) {
        List<ReservaDTO> resultado = new ArrayList<>();
        for (ReservaDTO reserva : baseDeDatos) {
            if (reserva.getVuelo() != null && reserva.getVuelo().equals(vuelo)) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public List<ReservaDTO> findByOrigenDestino(String origen, String destino) {
        List<ReservaDTO> resultado = new ArrayList<>();
        for (ReservaDTO reserva : baseDeDatos) {
            boolean coincideOrigen = (origen == null || origen.equals(reserva.getOrigen()));
            boolean coincideDestino = (destino == null || destino.equals(reserva.getDestino()));
            if (coincideOrigen && coincideDestino) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    // ----------- Utilitarios ------------

    public boolean existsById(String id) {
        return findById(id) != null;
    }

    public long count() {
        return baseDeDatos.size();
    }

    public long countByEstado(String estado) {
        return baseDeDatos.stream().filter(r -> estado.equals(r.getEstado())).count();
    }
}
