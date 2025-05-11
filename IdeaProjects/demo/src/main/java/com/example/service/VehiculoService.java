package com.example.service;

import com.example.model.Cliente;
import com.example.model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoService {

    public static VehiculoService instancia;
    private final List<Vehiculo> listaVehiculos = new ArrayList<>();

    private VehiculoService(){

    }

    public static VehiculoService getInstancia(){
        if (instancia == null){
            instancia = new VehiculoService();
        }
        return instancia;
    }

    //------------------------------- CRUDS -----------------------------//

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equals(vehiculo.getPlaca())) {
                return false;
            }
        }
        listaVehiculos.add(vehiculo);
        return true;
    }

    public List<Vehiculo> obtenerVehiculos(){
        return listaVehiculos;
    }


}
