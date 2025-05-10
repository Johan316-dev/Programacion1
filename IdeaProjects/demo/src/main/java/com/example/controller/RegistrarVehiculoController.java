package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegistrarVehiculoController {

    @FXML
    private ComboBox<?> cmbCliente;

    @FXML
    private ComboBox<?> cmbCombustible;

    @FXML
    private ComboBox<?> cmbPeriodo;

    @FXML
    private ComboBox<?> cmbTipoCamion;

    @FXML
    private ComboBox<?> cmbTipoMoto;

    @FXML
    private ComboBox<?> cmbTipoVehiculo;

    @FXML
    private ComboBox<?> cmbTransmision;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private Label lblClienteSeleccionado;

    @FXML
    private Label lblErrorColor;

    @FXML
    private Label lblErrorModelo;

    @FXML
    private Label lblErrorPlaca;

    @FXML
    private Label lblErrorTipo;

    @FXML
    private Label lblMensajeError;

    @FXML
    private VBox panelAutomovil;

    @FXML
    private VBox panelCamion;

    @FXML
    private VBox panelMembresia;

    @FXML
    private VBox panelMoto;

    @FXML
    private RadioButton rbConMembresia;

    @FXML
    private RadioButton rbSinMembresia;

    @FXML
    private Spinner<?> spNumEjes;

    @FXML
    private Spinner<?> spNumPuertas;

    @FXML
    private TextField txtCapacidadCarga;

    @FXML
    private TextField txtCilindraje;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtFechaFin;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private ToggleGroup grupoMembresia;


    @FXML
    void buscarCliente(ActionEvent event) {

    }

    @FXML
    void cancelarRegistro(ActionEvent event) {

    }

    @FXML
    void nuevoCliente(ActionEvent event) {

    }

    @FXML
    void registrarVehiculo(ActionEvent event) {

    }

}
