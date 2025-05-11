package com.example.model;

public class Automovil extends Vehiculo{

    private int numPuertas;
    private String combustible;
    private String transmision;

    public Automovil(String placa, String modelo, String color, int numPuertas, String combustible, String transmision) {
        super(placa, modelo, color);
        this.numPuertas = numPuertas;
        this.combustible = combustible;
        this.transmision = transmision;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    @Override
    public String toString() {
        return "Automovil{" +
                "placa='" + getPlaca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", color='" + getColor() + '\'' +
                "numPuertas=" + getNumPuertas() + '\'' +
                ", combustible='" + getCombustible() + '\'' +
                ", transmision='" + getTransmision() + '\'' +
                '}';
    }
}
