package com.example.controller;

import com.example.model.Cliente;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SeleccionarClienteController {

    @FXML
    private TableColumn<Cliente, String> colDocumento;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableView<Cliente> tablaClientes;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//


    private Cliente clienteSeleccionado;

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("id"));

        tablaClientes.setItems(FXCollections.observableArrayList(ClienteService.getInstancia().obtenerClientes()));
    }

    @FXML
    public void seleccionarCliente(ActionEvent event) {
        clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            ((Stage) tablaClientes.getScene().getWindow()).close();
        }
    }

    public void cerrarVista(ActionEvent event) {

        // Obtener el Stage (ventana) desde el botón o cualquier otro componente
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); // Cierra solo esta ventana
    }
}


