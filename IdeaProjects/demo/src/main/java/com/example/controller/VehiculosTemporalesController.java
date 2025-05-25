package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class VehiculosTemporalesController {

    @FXML
    private ComboBox<?> cmbFiltroTipo;

    @FXML
    private ComboBox<?> cmbPuestoAsignado;

    @FXML
    private ComboBox<?> cmbTipoVehiculo;

    @FXML
    private TableColumn<?, ?> colAcciones;

    @FXML
    private TableColumn<?, ?> colFechaIngreso;

    @FXML
    private TableColumn<?, ?> colHoraIngreso;

    @FXML
    private TableColumn<?, ?> colPlacaActivos;

    @FXML
    private TableColumn<?, ?> colPuestoActivos;

    @FXML
    private TableColumn<?, ?> colTarifaActual;

    @FXML
    private TableColumn<?, ?> colTicket;

    @FXML
    private TableColumn<?, ?> colTiempoTranscurrido;

    @FXML
    private TableColumn<?, ?> colTipoActivos;

    @FXML
    private Label lblCambio;

    @FXML
    private Label lblDescuentosRecargos;

    @FXML
    private Label lblDisponiblesAutomoviles;

    @FXML
    private Label lblDisponiblesCamiones;

    @FXML
    private Label lblDisponiblesMotos;

    @FXML
    private Label lblEstadoSistema;

    @FXML
    private Label lblFechaHoraActual;

    @FXML
    private Label lblFechaHoraIngreso;

    @FXML
    private Label lblFechaHoraIngresoSalida;

    @FXML
    private Label lblFechaHoraSalida;

    @FXML
    private Label lblNumeroTicket;

    @FXML
    private Label lblPlacaSalida;

    @FXML
    private Label lblPuestoSalida;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTarifaPorHora;

    @FXML
    private Label lblTiempoPermanencia;

    @FXML
    private Label lblTipoVehiculoSalida;

    @FXML
    private Label lblTotalOcupados;

    @FXML
    private Label lblTotalPagar;

    @FXML
    private Label lblTotalVehiculosActivos;

    @FXML
    private Label lblUltimaSalida;

    @FXML
    private Label lblUltimoIngreso;

    @FXML
    private VBox panelInformacionVehiculo;

    @FXML
    private RadioButton rbBuscarPorPlaca;

    @FXML
    private RadioButton rbBuscarPorTicket;

    @FXML
    private RadioButton rbEfectivo;

    @FXML
    private RadioButton rbTarjeta;

    @FXML
    private RadioButton rbTransferencia;

    @FXML
    private TableView<?> tablaVehiculosActivos;

    @FXML
    private TextField txtBusquedaSalida;

    @FXML
    private TextField txtFiltroPlaca;

    @FXML
    private TextField txtMontoRecibido;

    @FXML
    private TextField txtObservacionesIngreso;

    @FXML
    private TextField txtPlacaIngreso;

    @FXML
    private ToggleGroup grupoBusqueda;

    @FXML
    private ToggleGroup grupoMetodoPago;

    @FXML
    void actualizarListaVehiculos(ActionEvent event) {

    }

    @FXML
    void buscarVehiculo(ActionEvent event) {

    }

    @FXML
    void cancelarSalida(ActionEvent event) {

    }

    @FXML
    void filtrarVehiculos(ActionEvent event) {

    }

    @FXML
    void imprimirTicket(ActionEvent event) {

    }

    @FXML
    void limpiarFormularioIngreso(ActionEvent event) {

    }

    @FXML
    void procesarSalidaSeleccionado(ActionEvent event) {

    }

    @FXML
    void registrarIngreso(ActionEvent event) {

    }

    @FXML
    void registrarSalida(ActionEvent event) {

    }

}
