package com.example.model;

public class Cupo {
    private String numero;
    private String tipoVehiculo;
    private Boolean disponible;
    private String ubicacion;

    public Cupo(String numero, String tipoVehiculo, Boolean disponible, String ubicacion) {
        this.numero = numero;
        this.tipoVehiculo = tipoVehiculo;
        this.disponible = disponible;
        this.ubicacion = ubicacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
