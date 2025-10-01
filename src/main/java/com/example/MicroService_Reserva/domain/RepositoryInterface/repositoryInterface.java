package com.example.MicroService_Reserva.domain.RepositoryInterface;

import com.example.MicroService_Reserva.domain.dto.ReservaDTO;

import java.util.Optional;

public interface repositoryInterface {


    Iterable<ReservaDTO> findAll();

    // Consultar por ID
    Optional<ReservaDTO> findById(Long id);

    Optional<ReservaDTO> findById(String id);

    // Guardar
    ReservaDTO save(ReservaDTO reservaDTO);

    // Actualizar
    ReservaDTO update(ReservaDTO reservaDTO);

    // Eliminar
    void delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    void delete(String id);

    boolean existsById(String id);

    // Contar todos los registros
    long count();

    // Consultar por usuario
    Optional<ReservaDTO> findByUsuario(String usuario);

    // Consultar por vuelo
    Optional<ReservaDTO> findByVuelo(String vuelo);
}
