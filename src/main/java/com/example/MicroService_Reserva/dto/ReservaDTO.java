package com.example.MicroService_Reserva.dto;

import java.time.LocalDate;

public class ReservaDTO {
    private String id;
    private String usuario;
    private String vuelo;
    private String origen;
    private String destino;
    private String precio;
    private String estado;
    private LocalDate fechaVueloInicio;
    private LocalDate fechaVueloFinal;
    private String asiento;


    public ReservaDTO() {}


    public ReservaDTO(String id, String usuario, String vuelo, String origen, String destino,
                      String precio, String estado, LocalDate fechaVueloInicio,LocalDate fechaVueloFinal, String asiento) {
        this.id = id;
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.estado = estado;
        this.fechaVueloInicio = fechaVueloInicio;
        this.fechaVueloFinal = fechaVueloFinal;
        this.asiento = asiento;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getVuelo() { return vuelo; }
    public void setVuelo(String vuelo) { this.vuelo = vuelo; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getPrecio() { return precio; }
    public void setPrecio(String precio) { this.precio = precio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaVuelo() { return fechaVueloInicio; }
    public void setFechaVuelo(LocalDate fechaVuelo) { this.fechaVueloInicio = fechaVuelo; }

    public LocalDate getFechaVueloFinal() { return fechaVueloFinal; }
    public void setFechaVueloFinal(LocalDate fechaVuelo) { this.fechaVueloFinal = fechaVuelo; }
    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id='" + id + '\'' +
                ", usuario='" + usuario + '\'' +
                ", vuelo='" + vuelo + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", precio='" + precio + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaVuelo='" + fechaVueloInicio + '\'' +
                ", asiento='" + asiento + '\'' +
                '}';
    }
}


