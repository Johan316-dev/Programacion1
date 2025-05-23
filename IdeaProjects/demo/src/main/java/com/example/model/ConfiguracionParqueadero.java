package com.example.model;

import java.time.LocalDateTime;

public class ConfiguracionParqueadero {

    private static ConfiguracionParqueadero instancia;

    private Cupo cupo;
    private Tarifa tarifa;

    private ConfiguracionParqueadero() {
        cupo = new Cupo(30, 5,15, 1, 20, 3);     // Puedes inicializar con valores por defecto
        tarifa = new Tarifa(1000, 2000, 5000, 10000, 20000,30000, 10);
    }

    public static ConfiguracionParqueadero getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionParqueadero();
        }
        return instancia;
    }

    public Cupo getCupo() {
        return cupo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void actualizarUltimaActualizacion() {
        this.ultimaActualizacion = LocalDateTime.now();
    }

    private LocalDateTime ultimaActualizacion;

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }
}
