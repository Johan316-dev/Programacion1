package com.example.service;

import com.example.model.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoService {

    public static PagoService instancia;
    private final List<Pago> historial = new ArrayList<>();

    public static PagoService getInstancia() {

        if (instancia == null) {
            instancia = new PagoService();
        }

        return instancia;
    }

    public void registrarPago(Pago pago) {
        historial.add(pago);
    }

    public List<Pago> getHistorial() {
        return historial;
    }

}
