package com.example.controller;

import com.example.model.ConfiguracionParqueadero;
import com.example.model.Cupo;
import com.example.model.VehiculoTemporal;
import com.example.service.VehiculoTemporalService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrarIngresoController {

    @FXML
    private ComboBox<?> cmbPuestoAsignado;

    @FXML
    private ComboBox<String> cmbTipoVehiculo;

    @FXML
    private Label lblDisponiblesAutomoviles;

    @FXML
    private Label lblDisponiblesCamiones;

    @FXML
    private Label lblDisponiblesMotos;

    @FXML
    private Label lblFechaHoraIngreso;

    @FXML
    private Label lblTotalOcupados;

    @FXML
    private TextField txtObservacionesIngreso;

    @FXML
    private TextField txtPlacaIngreso;

    @FXML
    void limpiarFormularioIngreso(ActionEvent event) {

        txtPlacaIngreso.clear();
        cmbTipoVehiculo.getSelectionModel().clearSelection();
        txtObservacionesIngreso.clear();

    }

    @FXML
    public void initialize() {

        // Limitar a un máximo de 6 caracteres
        txtPlacaIngreso.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 6) {
                return change; // Permitir el cambio
            }
            return null; // Rechazar el cambio
        }));

        actualizarTarjetasCupos();
        actualizarEtiquetasDisponibilidad();

    }

    private void actualizarTarjetasCupos() {

        Cupo cupo = ConfiguracionParqueadero.getInstancia().getCupo();

        lblDisponiblesMotos.setText(
                cupo.getOcupadoMoto() + "/" + cupo.getMoto()
        );

        lblDisponiblesAutomoviles.setText(
                cupo.getOcupadoAutomovil() + "/" + cupo.getAutomovil()
        );

        lblDisponiblesCamiones.setText(
                cupo.getOcupadoCamion() + "/" + cupo.getCamion()
        );
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");

    public void iniciarReloj() {
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalDateTime ahora = LocalDateTime.now();
            lblFechaHoraIngreso.setText(ahora.format(formatter));
        }));
        reloj.setCycleCount(Timeline.INDEFINITE); // Para que se repita indefinidamente
        reloj.play();
    }

    @FXML
    void registrarIngreso(ActionEvent event) {

        if(cmbTipoVehiculo.getValue() == null){
            mostrarAlerta("Error", "Seleccione un tipo de vehiculo");
            return;
        }

        String placa = txtPlacaIngreso.getText().trim().toUpperCase();
        String tipoVehiculo = cmbTipoVehiculo.getValue();

        if (VehiculoTemporalService.estaPlacaRegistrada(placa)) {
            mostrarAlerta("Error", "Ya hay un vehículo con esta placa en el parqueadero.");
            return;
        }


        // Validar formato de placas según tipo de vehículo
        if (!validarPlaca(placa, tipoVehiculo)) {
            return;
        }
        
        Cupo cupo = ConfiguracionParqueadero.getInstancia().getCupo();

        switch (tipoVehiculo) {
            case "Moto":
                cupo.setOcupadoMoto(cupo.getOcupadoMoto() + 1);
                break;
            case "Automóvil":
                cupo.setOcupadoAutomovil(cupo.getOcupadoAutomovil() + 1);
                break;
            case "Camión":
                cupo.setOcupadoCamion(cupo.getOcupadoCamion() + 1);
                break;
        }

        VehiculoTemporal vehiculo = new VehiculoTemporal(
                placa, tipoVehiculo, LocalDateTime.now()
        );
        VehiculoTemporalService.registrarVehiculo(vehiculo);

        actualizarEtiquetasDisponibilidad();
        ConfiguracionParqueadero.getInstancia().notificarActualizacionCupos();
        mostrarAlerta("Ingreso registrado", "Ingreso del vehiculo " + vehiculo.getPlaca() + " registrado correctamente.");

    }

    private boolean validarPlaca(String placa, String tipoVehiculo) {

        // Validar que la placa no esté vacía
        if (placa.isEmpty()) {
            mostrarAlerta("Error", "La placa no puede estar vacía");
            return false;
        }

        // Validar longitud máxima
        if (placa.length() > 6) {
            mostrarAlerta("Error", "La placa no puede tener más de 6 caracteres");
            return false;
        }

        // Validar formato según tipo de vehículo
        switch (tipoVehiculo) {
            case "Moto":
                if (!placa.matches("^[A-Z]{3}\\d{2}[A-Z]$")) {
                    mostrarAlerta("Error", "Formato de placa inválido para moto. Debe ser ABC12D");
                    return false;
                }
                break;
            case "Automóvil":
            case "Camión":
                if (!placa.matches("^[A-Z]{3}\\d{3}$")) {
                    mostrarAlerta("Error", "Formato de placa inválido. Debe ser ABC123");
                    return false;
                }
                break;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void actualizarEtiquetasDisponibilidad() {

        Cupo cupo = ConfiguracionParqueadero.getInstancia().getCupo();

        lblDisponiblesMotos.setText((cupo.getOcupadoMoto()) + "/" + cupo.getMoto());
        lblDisponiblesAutomoviles.setText((cupo.getOcupadoAutomovil()) + "/" + cupo.getAutomovil());
        lblDisponiblesCamiones.setText((cupo.getOcupadoCamion()) + "/" + cupo.getCamion());

        int total = cupo.getAutomovil() + cupo.getMoto() + cupo.getCamion();
        int ocupados = cupo.getOcupadoAutomovil() + cupo.getOcupadoMoto() + cupo.getOcupadoCamion();
        lblTotalOcupados.setText(ocupados + "/" + total);
    }


}
