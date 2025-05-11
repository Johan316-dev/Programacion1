package com.example.model;

public class Vehiculo {
    private String placa;
    private String modelo;
    private String color;

    public Vehiculo(String placa,  String modelo, String color) {
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + getPlaca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", color='" + getModelo() + '\'' +
                '}';
    }
}
