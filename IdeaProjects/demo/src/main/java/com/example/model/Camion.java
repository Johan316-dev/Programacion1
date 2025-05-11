package com.example.model;

public class Camion extends Vehiculo{

    private int capacidadCarga;
    private String tipoCamion;
    private int numEjes;

    public Camion(String placa, String modelo, String color, int capacidadCarga, String tipoCamion, int numEjes) {
        super(placa, modelo, color);
        this.capacidadCarga = capacidadCarga;
        this.tipoCamion = tipoCamion;
        this.numEjes = numEjes;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getTipoCamion() {
        return tipoCamion;
    }

    public void setTipoCamion(String tipoCamion) {
        this.tipoCamion = tipoCamion;
    }

    public int getNumEjes() {
        return numEjes;
    }

    public void setNumEjes(int numEjes) {
        this.numEjes = numEjes;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "placa='" + getPlaca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", color='" + getColor() + '\'' +
                "capacidadCarga=" + getCapacidadCarga() +
                ", tipoCamion='" + getTipoCamion() + '\'' +
                ", numEjes=" + getNumEjes() +
                '}';
    }
}
