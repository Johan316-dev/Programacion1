package com.example.model;

public class Moto extends Vehiculo{

    private int cilindraje;
    private String tipoMoto;

    public Moto(String placa, String modelo, String color, int cilindraje, String tipoMoto) {
        super(placa, modelo, color);
        this.cilindraje = cilindraje;
        this.tipoMoto = tipoMoto;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTipoMoto() {
        return tipoMoto;
    }

    public void setTipoMoto(String tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "placa='" + getPlaca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", color='" + getColor() + '\'' +
                "cilindraje=" + getCilindraje() +
                ", tipoMoto='" + getTipoMoto() + '\'' +
                '}';
    }
}
