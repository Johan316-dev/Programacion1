package com.example.controller;

import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuscarVehiculoController {

    @FXML
    private Button btnGestionarMembresia;

    @FXML
    private ComboBox<?> cmbCriterioBusqueda;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colEstadoMembresia;

    @FXML
    private TableColumn<?, ?> colModelo;

    @FXML
    private TableColumn<?, ?> colPlaca;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colVencimiento;

    @FXML
    private Label lblCedulaClienteDetalle;

    @FXML
    private Label lblClienteDetalle;

    @FXML
    private Label lblColorDetalle;

    @FXML
    private Label lblDetallesEspecificos;

    @FXML
    private Label lblEstadoMembresiaDetalle;

    @FXML
    private Label lblModeloDetalle;

    @FXML
    private Label lblPlacaDetalle;

    @FXML
    private Label lblResultados;

    @FXML
    private Label lblTipoDetalle;

    @FXML
    private Label lblVencimientoDetalle;

    @FXML
    private VBox panelDetallesEspecificos;

    @FXML
    private VBox panelDetallesVehiculo;

    @FXML
    private TableView<?> tablaVehiculos;

    @FXML
    private TextField txtCliente;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    @FXML
    void buscarVehiculos(ActionEvent event) {

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
    void editarVehiculo(ActionEvent event) {

    }

    @FXML
    void exportarResultados(ActionEvent event) {

    }

    @FXML
    void gestionarMembresia(ActionEvent event) {

    }

    @FXML
    void limpiarFiltros(ActionEvent event) {

    }

    @FXML
    void registrarNuevoVehiculo(ActionEvent event) {

    }

    @FXML
    void verDetallesVehiculo(ActionEvent event) {

    }

    @FXML
    void verHistorialIngresos(ActionEvent event) {

    }

}
