package com.example.model;

public class Parqueadero {
    private String nombre;
    private String direccion;
    private String representante;
    private String telefono;

    public Parqueadero(String nombre, String direccion, String representante, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.representante = representante;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
