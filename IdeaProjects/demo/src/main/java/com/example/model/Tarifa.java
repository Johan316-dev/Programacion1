package com.example.model;

public class Tarifa {
    private String tipoVehiculo;
    private Double precioHora;
    private Double precioDia;
    private Double precioMembresia;

    public Tarifa(String tipoVehiculo, Double precioHora, Double precioDia, Double precioMembresia) {
        this.tipoVehiculo = tipoVehiculo;
        this.precioHora = precioHora;
        this.precioDia = precioDia;
        this.precioMembresia = precioMembresia;
    }


    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public Double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Double precioDia) {
        this.precioDia = precioDia;
    }

    public Double getPrecioMembresia() {
        return precioMembresia;
    }

    public void setPrecioMembresia(Double precioMembresia) {
        this.precioMembresia = precioMembresia;
    }
}
