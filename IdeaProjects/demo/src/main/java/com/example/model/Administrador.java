package com.example.model;

public class Administrador {

    private String id;
    private String nombre;
    //private String telefono;

    /**
     * Constructor de la clase Administrador
     * @param id
     * @param nombre
     */

    public Administrador(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Administrador() {
    }

    /**
     * Getters and setters
     * @return
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return nombre;
    }
}
