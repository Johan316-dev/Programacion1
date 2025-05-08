package com.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente{

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty correo = new SimpleStringProperty();

    public Cliente(String id, String nombre, String telefono, String correo) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.telefono.set(telefono);
        this.correo.set(correo);
    }

    // Getters y setters con propiedades observables
    public StringProperty idProperty() { return id; }
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty telefonoProperty() { return telefono; }
    public StringProperty correoProperty() { return correo; }

    public String getId() { return id.get(); }
    public void setId(String value) { id.set(value); }

    public String getNombre() { return nombre.get(); }
    public void setNombre(String value) { nombre.set(value); }

    public String getTelefono() { return telefono.get(); }
    public void setTelefono(String value) { telefono.set(value); }

    public String getCorreo() { return correo.get(); }
    public void setCorreo(String value) { correo.set(value); }
}