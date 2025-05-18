package com.example.controller;

import com.example.model.Automovil;
import com.example.model.Camion;
import com.example.model.Moto;
import com.example.model.Vehiculo;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarVehiculoController {

    @FXML
    private Button btnGestionarMembresia;

    @FXML
    private ComboBox<?> cmbCombustible;

    @FXML
    private ComboBox<?> cmbTipoCamion;

    @FXML
    private ComboBox<?> cmbTipoMoto;

    @FXML
    private ComboBox<String> cmbTipoVehiculo;

    @FXML
    private ComboBox<String> cmbTransmision;

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
    private VBox panelMoto;

    @FXML
    private Spinner<Integer> spNumEjes;

    @FXML
    private Spinner<Integer> spNumPuertas;

    @FXML
    private TextField txtCapacidadCarga;

    @FXML
    private TextField txtCilindraje;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtEstadoMembresia;

    @FXML
    private TextField txtFechaVencimiento;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    private Vehiculo vehiculo;

    @FXML
    public void initialize() {
        cmbTipoVehiculo.valueProperty().addListener((obs, oldVal, newVal) -> {
            mostrarPanelPorTipo(newVal);
        });
    }

    private void mostrarPanelPorTipo(String tipo) {
        panelAutomovil.setVisible(false); panelAutomovil.setManaged(false);
        panelMoto.setVisible(false); panelMoto.setManaged(false);
        panelCamion.setVisible(false); panelCamion.setManaged(false);

        switch (tipo) {
            case "Automovil":
                panelAutomovil.setVisible(true); panelAutomovil.setManaged(true);
                break;
            case "Moto":
                panelMoto.setVisible(true); panelMoto.setManaged(true);
                break;
            case "Camion":
                panelCamion.setVisible(true); panelCamion.setManaged(true);
                break;
        }
    }



    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        cargarDatosVehiculo();
    }

    private void cargarDatosVehiculo() {
        if (vehiculo != null) {
            txtPlaca.setText(vehiculo.getPlaca());
            txtModelo.setText(vehiculo.getModelo());
            txtColor.setText(vehiculo.getColor());

            if (vehiculo.getCliente() != null) {
                txtCliente.setText(vehiculo.getCliente().getNombre());
            }

            if (vehiculo.getMembresia() != null) {
                txtEstadoMembresia.setText(vehiculo.getMembresia().getEstado());
                txtFechaVencimiento.setText(vehiculo.getMembresia().getFechaFin().toString());
            }else{
                txtEstadoMembresia.setText("Sin membresia");
                txtFechaVencimiento.setText("Sin fecha de vencimiento");
            }

            // Deshabilitar los campos para que no se puedan editar
            txtEstadoMembresia.setDisable(true);
            txtFechaVencimiento.setDisable(true);

            // Establecer el tipo en el ComboBox
            String tipoVehiculo = vehiculo.getClass().getSimpleName();
            cmbTipoVehiculo.setValue(tipoVehiculo);

            // Mostrar panel según tipo
            mostrarPanelPorTipo(tipoVehiculo);

            // Cargar campos específicos
            if (vehiculo instanceof Automovil automovil) {
                spNumPuertas.getValueFactory().setValue(automovil.getNumPuertas());
            } else if (vehiculo instanceof Moto moto) {
                txtCilindraje.setText(String.valueOf(moto.getCilindraje()));
            } else if (vehiculo instanceof Camion camion) {
                txtCapacidadCarga.setText(String.valueOf(camion.getCapacidadCarga()));
            }
        }
    }


    @FXML
    void cambiarCliente(ActionEvent event) {

    }

    @FXML
    void gestionarMembresia(ActionEvent event) {

    }

}
