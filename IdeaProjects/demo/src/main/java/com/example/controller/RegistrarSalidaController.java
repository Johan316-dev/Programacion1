package com.example.controller;

import com.example.model.ConfiguracionParqueadero;
import com.example.model.Cupo;
import com.example.model.Tarifa;
import com.example.model.VehiculoTemporal;
import com.example.service.VehiculoTemporalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RegistrarSalidaController {

    @FXML
    private Label lblCambio;

    @FXML
    private Label lblDescuentosRecargos;

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
    private Label lblTotalPagar;

    @FXML
    private VBox panelInformacionVehiculo;

    @FXML
    private RadioButton rbEfectivo;

    @FXML
    private RadioButton rbTarjeta;

    @FXML
    private RadioButton rbTransferencia;

    @FXML
    private TextField txtBusquedaSalida;

    @FXML
    private TextField txtMontoRecibido;

    @FXML
    private ToggleGroup grupoMetodoPago;



    private VehiculoTemporal vehiculo;

    // Método para establecer la placa en el campo de texto
    public void setPlaca(String placa) {
        txtBusquedaSalida.setText(placa);
    }

    public void initialize() {

        //RADIO BUTTOM

        ToggleGroup grupoMetodoPago = new ToggleGroup();
        rbEfectivo.setToggleGroup(grupoMetodoPago);
        rbTarjeta.setToggleGroup(grupoMetodoPago);
        rbTransferencia.setToggleGroup(grupoMetodoPago);

        // Opcional: establecer uno seleccionado por defecto
        rbEfectivo.setSelected(true);

    }



    @FXML
    void buscarVehiculo(ActionEvent event) {

        String placa = txtBusquedaSalida.getText().trim().toUpperCase();

        if (placa.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una placa para buscar.");
            return;
        }

        VehiculoTemporal vehiculo = VehiculoTemporalService.buscarVehiculoPorPlaca(placa);

        if (vehiculo == null) {
            // Mostrar alerta si no hay selección
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Ningun vehiculo");
            alerta.setContentText("No existe vehiculo");
            alerta.showAndWait();
            panelInformacionVehiculo.setVisible(false);
            return;
        }

        // Mostrar información básica
        lblPlacaSalida.setText(vehiculo.getPlaca());
        lblTipoVehiculoSalida.setText(vehiculo.getTipoVehiculo());
        lblFechaHoraIngresoSalida.setText(formatearFechaHora(vehiculo.getFechaHoraIngreso()));

        // Fecha de salida actual
        LocalDateTime horaSalida = LocalDateTime.now();
        lblFechaHoraSalida.setText(formatearFechaHora(horaSalida));

        // Calcular permanencia y tarifa
        Duration duracion = Duration.between(vehiculo.getFechaHoraIngreso(), horaSalida);
        long minutos = duracion.toMinutes();
        long horas = Math.max(1, (minutos + 59) / 60);// Al menos una hora

        String tiempoPermanencia = String.format("%d horas %d minutos", minutos / 60, minutos % 60);
        lblTiempoPermanencia.setText(tiempoPermanencia);

        int tarifaHora = obtenerTarifaPorHora(vehiculo.getTipoVehiculo()); // define esta lógica
        int subtotal = (int) horas * tarifaHora;

        lblTarifaPorHora.setText(String.valueOf(tarifaHora));
        lblSubtotal.setText(String.valueOf(subtotal));
        lblDescuentosRecargos.setText("0"); // puedes agregar lógica más adelante
        lblTotalPagar.setText(String.valueOf(subtotal));

        lblCambio.setText("0");
        txtMontoRecibido.clear();

        panelInformacionVehiculo.setVisible(true);
        //actualizarCupos(vehiculo.getTipoVehiculo());
    }

    @FXML
    void registrarSalida(ActionEvent actionEvent) {

        String placa = txtBusquedaSalida.getText().trim().toUpperCase();

        VehiculoTemporal vehiculo = VehiculoTemporalService.buscarVehiculoPorPlaca(placa);

        if (vehiculo == null) {
            mostrarAlerta("Error", "Primero busque un vehículo para registrar su salida.");
            return;
        }

        double totalPagar = Double.parseDouble(lblTotalPagar.getText().replace(",", "").replace("$", ""));
        double montoRecibido;

        try {
            montoRecibido = Double.parseDouble(txtMontoRecibido.getText().trim());
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Ingrese un monto valido");
            alerta.showAndWait();
            return;
        }

        if (montoRecibido < totalPagar) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("El monto no cubre el total a pagar.");
            alerta.showAndWait();
            return;
        }

        // Calcular cambio
        double cambio = montoRecibido - totalPagar;
        lblCambio.setText(String.format("$ %, .0f", cambio));

        // Actualizar cupos
        Cupo cupo = ConfiguracionParqueadero.getInstancia().getCupo();
        switch (vehiculo.getTipoVehiculo()) {
            case "Moto":
                cupo.setOcupadoMoto(cupo.getOcupadoMoto() - 1);
                break;
            case "Automóvil":
                cupo.setOcupadoAutomovil(cupo.getOcupadoAutomovil() - 1);
                break;
            case "Camión":
                cupo.setOcupadoCamion(cupo.getOcupadoCamion() - 1);
                break;
        }

        // Eliminar del sistema
        VehiculoTemporalService.salidaVehiculo(placa);

        // (Opcional) Registrar en historial
        //HistorialService.registrarSalida(vehiculoActual, LocalDateTime.now(), totalPagar, métodoPago);

        // Actualizar etiquetas y estado
        ConfiguracionParqueadero.getInstancia().notificarActualizacionCupos();

        mostrarAlerta("Salida registrada", "Salida registrada correctamente.\nCambio: $" + String.format("%,.0f", cambio));

        //actualizarCupos(vehiculo.getTipoVehiculo());
        limpiarVistaSalida();

    }

    private void limpiarVistaSalida() {
        panelInformacionVehiculo.setVisible(false);
        vehiculo = null;
        txtBusquedaSalida.clear();
        txtMontoRecibido.clear();
        lblCambio.setText("0");
    }


    private void actualizarCupos(String tipoVehiculo) {
        Cupo cupo = ConfiguracionParqueadero.getInstancia().getCupo();
        switch (tipoVehiculo) {
            case "Moto":
                cupo.setOcupadoMoto(cupo.getOcupadoMoto() - 1);
                break;
            case "Automóvil":
                cupo.setOcupadoAutomovil(cupo.getOcupadoAutomovil() - 1);
                break;
            case "Camión":
                cupo.setOcupadoCamion(cupo.getOcupadoCamion() - 1);
                break;
        }
        ConfiguracionParqueadero.getInstancia().notificarActualizacionCupos();
    }

    private String formatearFechaHora(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        return fecha.format(formatter);
    }

    private int obtenerTarifaPorHora(String tipoVehiculo) {
        Tarifa tarifa = ConfiguracionParqueadero.getInstancia().getTarifa();
        switch (tipoVehiculo) {
            case "Moto":
                return tarifa.getTarifaHoraMoto();
            case "Automóvil":
                return tarifa.getTarifaHoraAuto();
            case "Camión":
                return tarifa.getTarifaHoraCamion();
            default:
                return 0;
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


}
