package com.example.service;

import com.example.model.VehiculoTemporal;

import java.util.ArrayList;
import java.util.List;

public class VehiculoTemporalService {

    private static final List<VehiculoTemporal> vehiculosEnParqueadero = new ArrayList<>();
    private static final List<VehiculoTemporal> historialVehiculos = new ArrayList<>();

    public static boolean estaPlacaRegistrada(String placa) {
        return vehiculosEnParqueadero.stream().anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public static void registrarVehiculo(VehiculoTemporal vehiculo) {
        vehiculosEnParqueadero.add(vehiculo);
        historialVehiculos.add(vehiculo);
    }

    public static void salidaVehiculo(String placa) {
        vehiculosEnParqueadero.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public static List<VehiculoTemporal> getVehiculosEnParqueadero() {
        return vehiculosEnParqueadero;
    }

    public static List<VehiculoTemporal> getHistorial() {
        return historialVehiculos;
    }

    public static VehiculoTemporal buscarVehiculoPorPlaca(String placa) {

        for (VehiculoTemporal v : vehiculosEnParqueadero) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public static void eliminarVehiculo(VehiculoTemporal vehiculo) {

        vehiculosEnParqueadero.remove(vehiculo);
    }
}
