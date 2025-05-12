package com.example.controller;

import com.example.model.*;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.Calendar;

public class RegistrarVehiculoController {

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private ComboBox<String> cmbCombustible;

    @FXML
    private ComboBox<?> cmbPeriodo;

    @FXML
    private ComboBox<String> cmbTipoCamion;

    @FXML
    private ComboBox<String> cmbTipoMoto;

    @FXML
    private ComboBox<String> cmbTipoVehiculo;

    @FXML
    private ComboBox<String> cmbTransmision;

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
    private Spinner<Integer> spNumEjes;

    @FXML
    private Spinner<Integer> spNumPuertas;

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

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    @FXML
    public void initialize() {

        //RADIO BUTTOM

        ToggleGroup grupoMembresia = new ToggleGroup();
        rbSinMembresia.setToggleGroup(grupoMembresia);
        rbConMembresia.setToggleGroup(grupoMembresia);

        // Opcional: establecer uno seleccionado por defecto
        rbSinMembresia.setSelected(true);

        //----//

        // Cargar clientes en el ComboBox
        List<Cliente> listaClientes = clienteService.obtenerClientes();
        cmbCliente.setItems(FXCollections.observableArrayList(listaClientes));

        cmbCliente.setOnAction(e -> {
            Cliente seleccionado = cmbCliente.getValue();
            if (seleccionado != null) {
                lblClienteSeleccionado.setText("Seleccionado: " + seleccionado.getNombre() + " (" + seleccionado.getId() + ")");
            } else {
                lblClienteSeleccionado.setText("No hay cliente seleccionado");
            }
        });

        // Cargar tipos de vehículos
        cmbTipoVehiculo.setItems(FXCollections.observableArrayList("Automóvil", "Moto", "Camión"));

        //  Manejar selección del tipo de vehículo
        cmbTipoVehiculo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Tipo seleccionado: " + newVal); // Debug
            ocultarTodosLosPaneles();

            if ("Automóvil".equals(newVal)) {
                panelAutomovil.setVisible(true);
                panelAutomovil.setManaged(true);
            } else if ("Moto".equals(newVal)) {
                panelMoto.setVisible(true);
                panelMoto.setManaged(true);
            } else if ("Camión".equals(newVal)) {
                panelCamion.setVisible(true);
                panelCamion.setManaged(true);
            }
        });

        //  Ocultar paneles al iniciar
        ocultarTodosLosPaneles();
    }



    private void ocultarTodosLosPaneles() {
        panelAutomovil.setVisible(false);
        panelAutomovil.setManaged(false);
        panelMoto.setVisible(false);
        panelMoto.setManaged(false);
        panelCamion.setVisible(false);
        panelCamion.setManaged(false);
    }

    @FXML
    void buscarCliente(ActionEvent event) {

    }

    @FXML
    void cancelarRegistro(ActionEvent event) {

        // Obtener el Stage (ventana) desde el botón o cualquier otro componente
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); // Cierra solo esta ventana

    }

    @FXML
    void nuevoCliente(ActionEvent event) {

    }

    @FXML
    void registrarVehiculo(ActionEvent event) {

        String tipo = cmbTipoVehiculo.getValue();
        String placa = txtPlaca.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();

        Vehiculo vehiculo = null;

        switch (tipo) {
            case "Automóvil":
                int puertas = spNumPuertas.getValue();
                String transmision = cmbTransmision.getValue();
                String combustible = cmbCombustible.getValue();
                vehiculo = new Automovil(placa, color, modelo, puertas, transmision, combustible);
                break;
            case "Moto":
                int cilindraje = Integer.parseInt(txtCilindraje.getText());
                String tipoMoto = cmbTipoMoto.getValue();
                vehiculo = new Moto(placa, color, modelo, cilindraje, tipoMoto);
                break;
            case "Camión":
                int carga = Integer.parseInt(txtCapacidadCarga.getText());
                int ejes = spNumEjes.getValue();
                String tipoCamion = cmbTipoCamion.getValue();
                vehiculo = new Camion(placa, modelo, color, carga, tipoCamion, ejes);
                break;
        }

        // Asociar membresía si se selecciona "Con Membresía"
        if (rbConMembresia.isSelected() && vehiculo != null) {
            Date fechaInicio = new Date(); // hoy
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicio);
            calendar.add(Calendar.MONTH, 2); // duración 2 meses
            Date fechaFin = calendar.getTime();

            Membresia membresia = new Membresia();
            membresia.setTipoMembresia("Estándar"); // puedes personalizar esto
            membresia.setFechaInicio(fechaInicio);
            membresia.setFechaFin(fechaFin);
            membresia.setCosto(40.000); // puedes hacerlo dinámico
            membresia.setEstado("Activa");

            vehiculo.setMembresia(membresia);
        }


        Cliente clienteSeleccionado = cmbCliente.getValue();

        if (clienteSeleccionado != null && vehiculo != null) {
            VehiculoService vehiculoService = VehiculoService.getInstancia();
            boolean registrado = vehiculoService.registrarVehiculo(vehiculo);

            if (registrado) {
                clienteSeleccionado.agregarVehiculo(vehiculo);
                mostrarAlerta("Vehiculo registrado", "El vehiculo del cliente " + clienteSeleccionado.getNombre()
                        + " se ha registrado correctamente");
                System.out.println("Lista de vehiculos registrados: ");
                for (Vehiculo v: vehiculoService.obtenerVehiculos()) {
                    System.out.println("- " + v.getPlaca() + " | " + v.getModelo());
                }
            } else {
                mostrarAlerta("Error","Ya existe un vehiculo con esa placa");
            }
        } else {
            mostrarAlerta("Advertencia","Debe seleccionar un cliente y completar los datos del vehículo.");
        }

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
