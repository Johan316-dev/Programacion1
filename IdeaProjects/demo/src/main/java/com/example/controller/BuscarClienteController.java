package com.example.controller;

import com.example.model.Cliente;
import com.example.service.ClienteService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

public class BuscarClienteController {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private ComboBox<String> cmbCriterioBusqueda;

    @FXML
    private TableColumn<Cliente, String> colCedula;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colVehiculos;

    @FXML
    private Label lblCedulaDetalle;

    @FXML
    private Label lblCorreoDetalle;

    @FXML
    private Label lblNombreDetalle;

    @FXML
    private Label lblResultados;

    @FXML
    private Label lblTelefonoDetalle;

    @FXML
    private VBox panelDetalles;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TextField txtBusqueda;

    ClienteService clienteService = ClienteService.getInstancia();

    @FXML
    public void initialize() {

        cmbCriterioBusqueda.getSelectionModel().selectFirst();

        // Asociar columnas a propiedades
        colCedula.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colVehiculos.setCellValueFactory(cellData -> new SimpleStringProperty("0")); // Placeholder

        // Cargar datos
        tablaClientes.setItems(FXCollections.observableArrayList(clienteService.obtenerClientes()));
    }

    @FXML
    private void buscarClientes() {
        String criterio = cmbCriterioBusqueda.getValue();
        String texto = txtBusqueda.getText().trim().toLowerCase();

        List<Cliente> filtrados;

        if (criterio == null || criterio.equals("Todos")) {
            filtrados = clienteService.obtenerClientes().stream()
                    .filter(cliente ->
                            cliente.getId().toLowerCase().contains(texto) ||
                                    cliente.getNombre().toLowerCase().contains(texto) ||
                                    cliente.getCorreo().toLowerCase().contains(texto) ||
                                    cliente.getTelefono().toLowerCase().contains(texto)
                    ).toList();
        } else {
            filtrados = clienteService.obtenerClientes().stream().filter(cliente -> {
                switch (criterio) {
                    case "Cédula":
                        return cliente.getId().toLowerCase().contains(texto);
                    case "Nombre":
                        return cliente.getNombre().toLowerCase().contains(texto);
                    case "Teléfono":
                        return cliente.getTelefono().toLowerCase().contains(texto);
                    default:
                        return false;
                }
            }).toList();
        }

        tablaClientes.setItems(FXCollections.observableArrayList(filtrados));
    }


    @FXML
    void cerrarVista(ActionEvent event) {

    }

    @FXML
    void editarCliente(ActionEvent event) {

    }

    @FXML
    void limpiarBusqueda(ActionEvent event) {
        txtBusqueda.clear();
        cmbCriterioBusqueda.getSelectionModel().selectFirst(); // Selecciona "Todos"
        tablaClientes.setItems(FXCollections.observableArrayList(clienteService.obtenerClientes()));

    }


    public void eliminar(ActionEvent actionEvent) {
    }
}
