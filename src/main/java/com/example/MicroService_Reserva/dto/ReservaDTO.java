package com.example.MicroService_Reserva.dto;

public class ReservaDTO {
    private String id;
    private String usuario;       // Nombre del pasajero o usuario
    private String vuelo;         // Código de vuelo (ej: AV123)
    private String origen;        // Ciudad de origen
    private String destino;       // Ciudad de destino
    private String precio;        // Precio del tiquete
    private String estado;        // PENDIENTE, CONFIRMADA, CANCELADA
    private String fechaVuelo;    // Fecha y hora del vuelo
    private String asiento;       // Número de asiento

    // Constructor vacío
    public ReservaDTO() {}

    // Constructor con parámetros
    public ReservaDTO(String id, String usuario, String vuelo, String origen, String destino,
                      String precio, String estado, String fechaVuelo, String asiento) {
        this.id = id;
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.estado = estado;
        this.fechaVuelo = fechaVuelo;
        this.asiento = asiento;
    }

    // Getters y Setters
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

    public String getFechaVuelo() { return fechaVuelo; }
    public void setFechaVuelo(String fechaVuelo) { this.fechaVuelo = fechaVuelo; }

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
                ", fechaVuelo='" + fechaVuelo + '\'' +
                ", asiento='" + asiento + '\'' +
                '}';
    }
}


