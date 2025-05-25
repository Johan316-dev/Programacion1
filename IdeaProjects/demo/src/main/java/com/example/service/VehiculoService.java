package com.example.service;

import com.example.model.Automovil;
import com.example.model.Cliente;
import com.example.model.Moto;
import com.example.model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoService {

    public static VehiculoService instancia;
    private final List<Vehiculo> listaVehiculos = new ArrayList<>();

    private Vehiculo vehiculoSeleccionado;

    private VehiculoService() {

        listaVehiculos.add(new Moto("XAM43D", "2016", "NEGRO", 150, "Naked"));
        listaVehiculos.add(new Automovil("FOR548", "2022", "ROJO", 4, "Diesel", "Manual"));


    }

    public static VehiculoService getInstancia() {
        if (instancia == null) {
            instancia = new VehiculoService();
        }
        return instancia;
    }

    // Establecer el vehículo actualmente seleccionado (por ejemplo, desde la UI)
    public void setVehiculoSeleccionado(Vehiculo vehiculo) {
        this.vehiculoSeleccionado = vehiculo;
    }

    // ✅ Obtener el vehículo actualmente seleccionado
    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
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

    public List<Vehiculo> obtenerVehiculos() {

        return listaVehiculos;
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {

        listaVehiculos.remove(vehiculo);
    }

    public boolean existeVehiculoConPlaca(String placa) {
        return listaVehiculos.stream().anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public boolean actualizarVehiculo(Vehiculo vehiculoActualizado, String placaAnterior) {

        List<Vehiculo> vehiculos = this.listaVehiculos;

        // Verifica que no exista un vehiculo con esa placa
        for (Vehiculo v : vehiculos) {
            if (!v.getPlaca().equals(placaAnterior) && v.getPlaca().equalsIgnoreCase(vehiculoActualizado.getPlaca())) {
                return false; // Ya existe otro con esa placa
            }
        }

        // Buscar el cliente original y actualizar sus datos
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPlaca().equals(placaAnterior)) {
                vehiculos.set(i, vehiculoActualizado);
                return true;
            }
        }

        return false;
    }

    // En VehiculoService
    private final List<ChangeListener> listeners = new ArrayList<>();

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyChange() {
        for (ChangeListener listener : listeners) {
            listener.onChange();
        }
    }

    // Interface
    public interface ChangeListener {
        void onChange();
    }


}
