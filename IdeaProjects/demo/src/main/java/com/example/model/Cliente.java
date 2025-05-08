package com.example.model;

public class Cliente{
    private String correo;
    private String telefono;
    private String nombre;
    private String id;

    public Cliente(String correo, String telefono, String nombre, String id) {
        this.correo = correo;
        this.telefono = telefono;
        this.nombre = nombre;
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}