package com.example.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.LocalDateTime;

public class ConfiguracionParqueadero {

    private static ConfiguracionParqueadero instancia;

    private Cupo cupo;
    private Tarifa tarifa;

    private ConfiguracionParqueadero() {
        cupo = new Cupo(30, 0,15, 0, 20, 0);     // Puedes inicializar con valores por defecto
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

    //----------------------------------------//

    //ACTUALIZAR ETIQUETAS DEL HOME

    private final BooleanProperty cuposActualizados = new SimpleBooleanProperty(false);

    public BooleanProperty cuposActualizadosProperty() {
        return cuposActualizados;
    }

    public void notificarActualizacionCupos() {
        cuposActualizados.set(!cuposActualizados.get()); // alterna el valor
    }
}
