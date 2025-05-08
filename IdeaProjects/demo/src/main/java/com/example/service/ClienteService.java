package com.example.service;

import com.example.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private static ClienteService instancia;
    private final List<Cliente> clientes = new ArrayList<>();

    private ClienteService() { }

    public static ClienteService getInstancia() {
        if (instancia == null) {
            instancia = new ClienteService();
        }
        return instancia;
    }

    public boolean agregarCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getId().equals(cliente.getId())) {
                return false;
            }
        }
        clientes.add(cliente);
        return true;
    }

    public List<Cliente> obtenerClientes() {
        return clientes;
    }
}

