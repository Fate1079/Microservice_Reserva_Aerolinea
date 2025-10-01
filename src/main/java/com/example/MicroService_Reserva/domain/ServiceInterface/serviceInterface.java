package com.example.MicroService_Reserva.domain.ServiceInterface;


import com.example.MicroService_Reserva.domain.dto.ReservaDTO;

public interface serviceInterface {


    Iterable<ReservaDTO> getAllReservas();

    ReservaDTO getReservaById(Long id);

    ReservaDTO saveReserva(ReservaDTO reserva);

    ReservaDTO updateReserva(Long id, ReservaDTO reserva);

    void deleteReserva(Long id);

    long countReservas();

    ReservaDTO getReservaByUsuario(String usuario);

    ReservaDTO getReservaByVuelo(String vuelo);


}
