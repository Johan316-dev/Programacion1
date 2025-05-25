package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VehiculosActivosController {

    @FXML
    private ComboBox<?> cmbFiltroTipo;

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
    private Label lblEstadoSistema;

    @FXML
    private Label lblFechaHoraActual;

    @FXML
    private Label lblTotalVehiculosActivos;

    @FXML
    private Label lblUltimaSalida;

    @FXML
    private Label lblUltimoIngreso;

    @FXML
    private TableView<?> tablaVehiculosActivos;

    @FXML
    private TextField txtFiltroPlaca;

    @FXML
    void actualizarListaVehiculos(ActionEvent event) {

    }

    @FXML
    void filtrarVehiculos(ActionEvent event) {

    }

    @FXML
    void procesarSalidaSeleccionado(ActionEvent event) {

    }

}
