package com.example.model;

import java.util.Date;

public class Factura {
    private Date fechaEmision;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Double montoTotal;

    public Factura(Date fechaEmision, Cliente cliente, Vehiculo vehiculo, Double montoTotal) {
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.montoTotal = montoTotal;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
