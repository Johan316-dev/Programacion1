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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    @FXML
    private DialogPane dialogpane;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    private Vehiculo vehiculo;

    private boolean vehiculoActualizado = false;

    public boolean isVehiculoActualizado() {
        return vehiculoActualizado;
    }

    private Runnable onCloseCallback;

    public void setOnCloseCallback(Runnable callback) {
        System.out.println("setOnCloseCallback");
        this.onCloseCallback = callback;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }




    @FXML
    public void initialize() {

        Button cancelButton = (Button) dialogpane.lookupButton(ButtonType.CANCEL);
        cancelButton.addEventFilter(ActionEvent.ACTION, event -> {
            // Cerrar el diálogo manualmente
            dialogpane.getScene().getWindow().hide();
        });

        // Obtener el botón OK y añadirle un filtro de evento
        Button okButton = (Button) dialogpane.lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            lblErrorPlaca.setVisible(false);
            lblErrorColor.setVisible(false);
            lblErrorModelo.setVisible(false);
            lblMensajeError.setVisible(false);

            String nuevaPlaca = txtPlaca.getText().trim();
            String nuevoModelo = txtModelo.getText().trim();
            String nuevoColor = txtColor.getText().trim();

            if (nuevaPlaca.isEmpty()) {
                lblErrorPlaca.setText("La placa no puede estar vacía");
                lblErrorPlaca.setVisible(true);
                event.consume();
                return;
            }

            if (nuevoModelo.isEmpty()) {
                lblErrorModelo.setText("El modelo no puede estar vacío");
                lblErrorModelo.setVisible(true);
                event.consume();
                return;
            }

            if (nuevoColor.isEmpty()) {
                lblErrorColor.setText("El color no puede estar vacío");
                lblErrorColor.setVisible(true);
                event.consume();
                return;
            }

            // Guardar placa original
            String placaAnterior = vehiculo.getPlaca();

            // Actualizar campos comunes
            vehiculo.setPlaca(nuevaPlaca);
            vehiculo.setModelo(nuevoModelo);
            vehiculo.setColor(nuevoColor);

            // Actualizar campos específicos
            if (vehiculo instanceof Automovil automovil) {
                automovil.setNumPuertas(spNumPuertas.getValue());
            } else if (vehiculo instanceof Moto moto) {
                try {
                    int cilindraje = Integer.parseInt(txtCilindraje.getText().trim());
                    moto.setCilindraje(cilindraje);
                } catch (NumberFormatException e) {
                    lblMensajeError.setText("Cilindraje inválido");
                    lblMensajeError.setVisible(true);
                    event.consume();
                    return;
                }
            } else if (vehiculo instanceof Camion camion) {
                try {
                    int capacidad = Integer.parseInt(txtCapacidadCarga.getText().trim());
                    camion.setCapacidadCarga(capacidad);
                } catch (NumberFormatException e) {
                    lblMensajeError.setText("Capacidad de carga inválida");
                    lblMensajeError.setVisible(true);
                    event.consume();
                    return;
                }
            }

            // Intentar actualizar usando el servicio
            boolean actualizado = vehiculoService.actualizarVehiculo(vehiculo, placaAnterior);
            if (!actualizado) {
                lblErrorPlaca.setText("Ya existe otro vehículo con esa placa");
                lblErrorPlaca.setVisible(true);
                event.consume();
            }else{
                // ✅ Cerrar la ventana
                mostrarAlerta("Vehiculo actualizado", "Se ha actualizado correctamente.");
                vehiculoService.notifyChange();
                if (onCloseCallback != null) {
                    System.out.println("onCloseCallback");
                    onCloseCallback.run();
                }
                vehiculoActualizado = true;
                dialogpane.getScene().getWindow().hide();
            }
        });


        cmbTipoVehiculo.valueProperty().addListener((obs, oldVal, newVal) -> {
            mostrarPanelPorTipo(newVal);
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
            cmbTipoVehiculo.setDisable(true);
            txtPlaca.setDisable(true);

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
