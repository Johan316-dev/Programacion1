package com.example.service;

import com.example.model.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    /**
     * creamos el array para los administradores
     */

    private final List<Administrador> administradores = new ArrayList<Administrador>();

    /**
     * quemamos un administrador en el arraylist
     */
    public AdminService(){
        administradores.add(new Administrador("1089", "johan"));
    }

    /**
     * metodo que valida el administrador en la lista y asi permitir el login
     * @param id
     * @param nombre
     * @return
     */

    public boolean validarLogin(String id, String nombre) {

        for (Administrador admin : administradores) {
            if (admin.getNombre().equals(nombre)  && admin.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
