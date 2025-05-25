package com.example.model;

import java.time.LocalDateTime;

public class VehiculoTemporal {

    private String placa;
    private String tipoVehiculo;
    private LocalDateTime fechaHoraIngreso;

    public VehiculoTemporal(String placa, String tipoVehiculo, LocalDateTime fechaHoraIngreso) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaHoraIngreso = fechaHoraIngreso;
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

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }
}
