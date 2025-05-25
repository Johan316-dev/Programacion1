package com.example.service;

import com.example.model.Membresia;

import java.util.ArrayList;
import java.util.List;

public class MembresiaService {

    private List<Membresia> membresiasActivas = new ArrayList<>();

    public void registrarMembresia(Membresia membresia) {
        membresiasActivas.add(membresia);
    }

    public List<Membresia> obtenerMembresiasActivas() {
        return membresiasActivas;
    }

    //INSTANCIA

    private static MembresiaService instancia;

    public static MembresiaService getInstancia() {
        if (instancia == null) {
            instancia = new MembresiaService();
        }
        return instancia;
    }
}
