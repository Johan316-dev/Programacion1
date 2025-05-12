package com.example.controller;

import com.example.model.Cliente;
import com.example.model.Membresia;
import com.example.model.Vehiculo;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehiculoClienteController {

    @FXML
    private Button btnGestionarMembresia;

    @FXML
    private TableColumn<Vehiculo, String> colColor;

    @FXML
    private TableColumn<Vehiculo, String> colEstadoMembresia;

    @FXML
    private TableColumn<Vehiculo, String> colModelo;

    @FXML
    private TableColumn<Vehiculo, String> colPlaca;

    @FXML
    private TableColumn<Vehiculo, String> colTipo;

    @FXML
    private TableColumn<Vehiculo, String> colVencimiento;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblColorDetalle;

    @FXML
    private Label lblCorreo;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblDetallesEspecificos;

    @FXML
    private Label lblEstadoMembresiaDetalle;

    @FXML
    private Label lblModeloDetalle;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPlacaDetalle;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblTipoDetalle;

    @FXML
    private Label lblVencimientoDetalle;

    @FXML
    private VBox panelDetallesEspecificos;

    @FXML
    private VBox panelDetallesVehiculo;

    @FXML
    private TableView<Vehiculo> tablaVehiculos;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    private Cliente cliente;

    private void configurarColumnas() {
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        colEstadoMembresia.setCellValueFactory(cellData -> {
            Membresia m = cellData.getValue().getMembresia();
            return new SimpleStringProperty(m != null ? m.getEstado() : "Sin Membresía");
        });

        colVencimiento.setCellValueFactory(cellData -> {
            Membresia m = cellData.getValue().getMembresia();
            if (m != null && m.getFechaFin() != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                return new SimpleStringProperty(df.format(m.getFechaFin()));
            }
            return new SimpleStringProperty("-");
        });
    }

    public void cargarVehiculosCliente() {
        if (cliente == null) {
            System.out.println("Error: El cliente proporcionado es nulo.");
            return;
        }

        System.out.println("Vehículos del cliente: " + cliente.getVehiculos().size());
        tablaVehiculos.setItems(FXCollections.observableArrayList(cliente.getVehiculos()));
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Error: El cliente asignado es nulo en setCliente.");
            return;
        }

        this.cliente = cliente;

        // Actualiza las etiquetas de la vista
        lblCedula.setText(String.valueOf(cliente.getId()));
        lblNombre.setText(cliente.getNombre());
        lblTelefono.setText(cliente.getTelefono());
        lblCorreo.setText(cliente.getCorreo());
        lblDescripcion.setText("Datos asociados al cliente " + cliente.getNombre());

        configurarColumnas();
        cargarVehiculosCliente(); // sin parámetro
    }


    public void cargarVehiculosCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Error: El cliente proporcionado es nulo en cargarVehiculosCliente.");
            return;
        }

        System.out.println("Cargando vehículos para el cliente: " + cliente.getNombre());

        List<Vehiculo> vehiculos = cliente.getVehiculos();
        if (vehiculos == null) {
            System.out.println("Error: La lista de vehículos es nula.");
            return;
        }

        if (vehiculos.isEmpty()) {
            System.out.println("El cliente no tiene vehículos registrados.");
        } else {
            System.out.println("Vehículos del cliente:");
            for (Vehiculo v : vehiculos) {
                System.out.println("- " + v.getPlaca() + " | " + v.getColor());
            }
        }

        tablaVehiculos.setItems(FXCollections.observableArrayList(vehiculos));

        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        colTipo.setCellValueFactory(cellData -> {
            String tipo = cellData.getValue().getClass().getSimpleName(); // Automovil, Moto, Camion
            return new SimpleStringProperty(tipo);
        });

        colEstadoMembresia.setCellValueFactory(cellData -> {
            Membresia m = cellData.getValue().getMembresia();
            String estado = (m != null) ? m.getEstado() : "Sin Membresía";
            return new SimpleStringProperty(estado);
        });

        colVencimiento.setCellValueFactory(cellData -> {
            Membresia m = cellData.getValue().getMembresia();
            if (m != null && m.getFechaFin() != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                return new SimpleStringProperty(df.format(m.getFechaFin()));
            }
            return new SimpleStringProperty("-");
        });
    }




    @FXML
    void cerrarDetalles(ActionEvent event) {

    }

    @FXML
    void cerrarVista(ActionEvent event) {

        // Obtener el Stage (ventana) desde el botón o cualquier otro componente
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); // Cierra solo esta ventana

    }

    @FXML
    void editarCliente(ActionEvent event) {

    }

    @FXML
    void editarVehiculo(ActionEvent event) {

    }

    @FXML
    void eliminarVehiculo(ActionEvent event) {

    }

    @FXML
    void gestionarMembresia(ActionEvent event) {

    }

    @FXML
    void verHistorialIngresos(ActionEvent event) {

    }

}
