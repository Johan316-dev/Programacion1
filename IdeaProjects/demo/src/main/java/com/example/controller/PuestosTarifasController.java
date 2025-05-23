package com.example.controller;

import com.example.model.ConfiguracionParqueadero;
import com.example.model.Cupo;
import com.example.model.Tarifa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PuestosTarifasController {

    @FXML
    private Label lblTotalPuestos;

    @FXML
    private Label lblUltimaActualizacion;

    @FXML
    private Spinner<Integer> spinnerAutomoviles;

    @FXML
    private Spinner<Integer> spinnerCamiones;

    @FXML
    private Spinner<Integer> spinnerHorasTarifaDiaria;

    @FXML
    private Spinner<Integer> spinnerMotos;

    @FXML
    private TextField txtTarifaDiaAutomoviles;

    @FXML
    private TextField txtTarifaDiaCamiones;

    @FXML
    private TextField txtTarifaDiaMotos;

    @FXML
    private TextField txtTarifaHoraAutomoviles;

    @FXML
    private TextField txtTarifaHoraCamiones;

    @FXML
    private TextField txtTarifaHoraMotos;

    @FXML
    public void initialize() {
        ConfiguracionParqueadero config = ConfiguracionParqueadero.getInstancia();

        Cupo cupo = config.getCupo();
        spinnerMotos.getValueFactory().setValue(cupo.getMoto());
        spinnerAutomoviles.getValueFactory().setValue(cupo.getAutomovil());
        spinnerCamiones.getValueFactory().setValue(cupo.getCamion());

        actualizarTotalPuestos();

        Tarifa tarifa = config.getTarifa();
        txtTarifaHoraMotos.setText(String.valueOf(tarifa.getTarifaHoraMoto()));
        txtTarifaHoraAutomoviles.setText(String.valueOf(tarifa.getTarifaHoraAuto()));
        txtTarifaHoraCamiones.setText(String.valueOf(tarifa.getTarifaHoraCamion()));

        txtTarifaDiaMotos.setText(String.valueOf(tarifa.getTarifaDiaMoto()));
        txtTarifaDiaAutomoviles.setText(String.valueOf(tarifa.getTarifaDiaAuto()));
        txtTarifaDiaCamiones.setText(String.valueOf(tarifa.getTarifaDiaCamion()));

        spinnerHorasTarifaDiaria.getValueFactory().setValue(tarifa.getHorasParaDia());

        mostrarUltimaActualizacion();
    }


    @FXML
    void cancelarCambios(ActionEvent event) {

            //Cerrar ventana
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close(); // Cierra solo esta ventana



    }

    @FXML
    void guardarCambios(ActionEvent event) {

        ConfiguracionParqueadero config = ConfiguracionParqueadero.getInstancia();

        Cupo cupo = config.getCupo();
        cupo.setMoto(spinnerMotos.getValue());
        cupo.setAutomovil(spinnerAutomoviles.getValue());
        cupo.setCamion(spinnerCamiones.getValue());

        Tarifa tarifa = config.getTarifa();
        tarifa.setTarifaHoraMoto(Integer.parseInt(txtTarifaHoraMotos.getText()));
        tarifa.setTarifaHoraAuto(Integer.parseInt(txtTarifaHoraAutomoviles.getText()));
        tarifa.setTarifaHoraCamion(Integer.parseInt(txtTarifaHoraCamiones.getText()));

        tarifa.setTarifaDiaMoto(Integer.parseInt(txtTarifaDiaMotos.getText()));
        tarifa.setTarifaDiaAuto(Integer.parseInt(txtTarifaDiaAutomoviles.getText()));
        tarifa.setTarifaDiaCamion(Integer.parseInt(txtTarifaDiaCamiones.getText()));
        tarifa.setHorasParaDia(spinnerHorasTarifaDiaria.getValue());

        config.actualizarUltimaActualizacion();
        mostrarUltimaActualizacion();
        actualizarTotalPuestos();

        // Registrar fecha de actualización
        ConfiguracionParqueadero.getInstancia().actualizarUltimaActualizacion();

        // Feedback opcional
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Guardados");
        alert.setHeaderText("Confirmacion de Cambios");
        alert.setContentText("Los datos del parqueadero se han actualizado correctamente");
        alert.showAndWait();
        System.out.println("Configuración guardada");

    }

    private void actualizarTotalPuestos() {
        int total = spinnerMotos.getValue() + spinnerAutomoviles.getValue() + spinnerCamiones.getValue();
        lblTotalPuestos.setText(total + " puestos");
    }

    private void mostrarUltimaActualizacion() {
        LocalDateTime fecha = ConfiguracionParqueadero.getInstancia().getUltimaActualizacion();
        if (fecha != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
            lblUltimaActualizacion.setText("Última actualización: " + fecha.format(formatter));
        }
    }


}
