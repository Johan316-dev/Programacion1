package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Pago {

    private String placa;
    private String tipoVehiculo;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private double totalPagado;
    private boolean conMembresia;

    public Pago(String placa, String tipoVehiculo, LocalDate fechaIngreso, LocalDate fechaSalida, double totalPagado, boolean conMembresia) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.totalPagado = totalPagado;
        this.conMembresia = conMembresia;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public boolean getConMembresia() {
        return conMembresia;
    }

    public void setConMembresia(boolean conMembresia) {
        this.conMembresia = conMembresia;
    }
}

