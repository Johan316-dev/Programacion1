package com.example.service;

import com.example.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private static ClienteService instancia;
    private final List<Cliente> clientes = new ArrayList<>();

    private ClienteService() {

        clientes.add(new Cliente("1001", "Johan", "123456789", "johan@gmail.com"));
        clientes.add(new Cliente("1002", "Jose", "987654321", "jose@gmail.com"));

    }

    public static ClienteService getInstancia() {
        if (instancia == null) {
            instancia = new ClienteService();
        }
        return instancia;
    }

    //-------------------------------------------- CRUDS -----------------------------//

    /**
     * Metodo para agregar un cliente a la lista
     * @param cliente
     * @return
     */
    public boolean agregarCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getId().equals(cliente.getId())) {
                return false;
            }
        }
        clientes.add(cliente);
        return true;
    }

    /**
     * Metodo que obtiene los clientes de la lista
     * @return
     */
    public List<Cliente> obtenerClientes() {

        return clientes;
    }


    public void eliminarCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public boolean actualizarCliente(String idAnterior, Cliente clienteActualizado) {
        // Verifica que no exista otro cliente con la nueva cédula
        for (Cliente c : clientes) {
            if (!c.getId().equals(idAnterior) && c.getId().equalsIgnoreCase(clienteActualizado.getId())) {
                return false; // Ya existe otro con esa cédula
            }
        }

        // Buscar el cliente original y actualizar sus datos
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(idAnterior)) {
                clientes.set(i, clienteActualizado);
                return true;
            }
        }

        return false;
    }




}

