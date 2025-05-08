package com.example.model;

import java.util.Date;

public class Pago {
    private Double monto;
    private String metodo;
    private Date fecha;
    private String estado;
    private String idFactura;

    public Pago(Double monto, String metodo, Date fecha, String estado, String idFactura) {
        this.monto = monto;
        this.metodo = metodo;
        this.fecha = fecha;
        this.estado = estado;
        this.idFactura = idFactura;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }
}
