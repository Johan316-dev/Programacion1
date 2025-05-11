package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class VehiculoClienteController {

    @FXML
    private Button btnGestionarMembresia;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colEstadoMembresia;

    @FXML
    private TableColumn<?, ?> colModelo;

    @FXML
    private TableColumn<?, ?> colPlaca;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colVencimiento;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblColorDetalle;

    @FXML
    private Label lblCorreo;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblDetallesEspecificos;

    @FXML
    private Label lblEstadoMembresiaDetalle;

    @FXML
    private Label lblModeloDetalle;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPlacaDetalle;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblTipoDetalle;

    @FXML
    private Label lblVencimientoDetalle;

    @FXML
    private VBox panelDetallesEspecificos;

    @FXML
    private VBox panelDetallesVehiculo;

    @FXML
    private TableView<?> tablaVehiculos;

    @FXML
    void cerrarDetalles(ActionEvent event) {

    }

    @FXML
    void cerrarVista(ActionEvent event) {

    }

    @FXML
    void editarCliente(ActionEvent event) {

    }

    @FXML
    void editarVehiculo(ActionEvent event) {

    }

    @FXML
    void eliminarVehiculo(ActionEvent event) {

    }

    @FXML
    void gestionarMembresia(ActionEvent event) {

    }

    @FXML
    void verHistorialIngresos(ActionEvent event) {

    }

}
