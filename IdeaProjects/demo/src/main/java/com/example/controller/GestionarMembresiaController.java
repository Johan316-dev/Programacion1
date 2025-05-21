package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GestionarMembresiaController {

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private Label lblCedulaCliente;

    @FXML
    private Label lblColor;

    @FXML
    private Label lblCorreoCliente;

    @FXML
    private Label lblDescuento;

    @FXML
    private Label lblEstadoMembresia;

    @FXML
    private Label lblFechaFin;

    @FXML
    private Label lblMensajeError;

    @FXML
    private Label lblModelo;

    @FXML
    private Label lblNombreCliente;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblPlanSeleccionado;

    @FXML
    private Label lblPrecio1Anio;

    @FXML
    private Label lblPrecio1Mes;

    @FXML
    private Label lblPrecio3Meses;

    @FXML
    private Label lblPrecioBase;

    @FXML
    private Label lblTelefonoCliente;

    @FXML
    private Label lblTipo;

    @FXML
    private Label lblTotalPagar;

    @FXML
    private VBox panelDatosPago;

    @FXML
    private RadioButton rbEfectivo;

    @FXML
    private RadioButton rbPlan1Anio;

    @FXML
    private RadioButton rbPlan1Mes;

    @FXML
    private RadioButton rbPlan3Meses;

    @FXML
    private RadioButton rbTarjeta;

    @FXML
    private RadioButton rbTransferencia;

    @FXML
    private VBox tarjeta1Anio;

    @FXML
    private VBox tarjeta1Mes;

    @FXML
    private VBox tarjeta3Meses;

    @FXML
    private TextArea txtObservaciones;

    @FXML
    private TextField txtReferenciaPago;

    @FXML
    private ToggleGroup grupoMetodoPago;

    @FXML
    private ToggleGroup grupoPlanes;

    @FXML
    void cancelarOperacion(ActionEvent event) {

    }

    @FXML
    void confirmarPago(ActionEvent event) {

    }

    @FXML
    void generarFactura(ActionEvent event) {

    }

    @FXML
    void seleccionarPlan1Anio(MouseEvent event) {

    }

    @FXML
    void seleccionarPlan1Mes(MouseEvent event) {

    }

    @FXML
    void seleccionarPlan3Meses(MouseEvent event) {

    }

}
