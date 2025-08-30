package com.example.MicroService_Reserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MicroServiceReservaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceReservaApplication.class, args);
    }
}