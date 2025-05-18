package com.example.controller;

import com.example.model.Cliente;
import com.example.model.HelloApplication;
import com.example.model.Membresia;
import com.example.model.Vehiculo;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class BuscarVehiculoController {

    @FXML
    private Button btnGestionarMembresia;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ComboBox<String> cmbCriterioBusqueda;

    @FXML
    private TableColumn<Vehiculo, String> colCliente;

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
    private TableView<Vehiculo> tablaVehiculos;

    @FXML
    private TextField txtCliente;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    @FXML
    public void initialize() {
        VehiculoService vehiculoService = VehiculoService.getInstancia();

        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculos();
        tablaVehiculos.setItems(FXCollections.observableArrayList(vehiculos));

        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName())
        );

        colCliente.setCellValueFactory(cellData -> {
            Cliente c = cellData.getValue().getCliente();
            String nombre = (c != null) ? c.getNombre() : "Desconocido";
            return new SimpleStringProperty(nombre);
        });

        colEstadoMembresia.setCellValueFactory(cellData -> {
            Membresia m = cellData.getValue().getMembresia();
            return new SimpleStringProperty((m != null) ? m.getEstado() : "Sin membresía");
        });

        colVencimiento.setCellValueFactory(cellData -> {
            Membresia m = cellData.getValue().getMembresia();
            if (m != null && m.getFechaFin() != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                return new SimpleStringProperty(df.format(m.getFechaFin()));
            }
            return new SimpleStringProperty("-");
        });

        lblResultados.setText(vehiculos.size() + " vehículos encontrados");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    void buscarVehiculos(ActionEvent event) {

        String criterioBusqueda = cmbCriterioBusqueda.getValue();
        String textoBusqueda = txtCliente.getText().toLowerCase().trim();

        List<Vehiculo> filtrados;

        if (criterioBusqueda == null || criterioBusqueda.equals("Todos")) {
            filtrados = vehiculoService.obtenerVehiculos().stream()
                    .filter(vehiculo ->
                            vehiculo.getPlaca().toLowerCase().contains(textoBusqueda) ||
                                    vehiculo.getColor().toLowerCase().contains(textoBusqueda) ||
                                    (vehiculo.getCliente() != null && (
                                            vehiculo.getCliente().getNombre().toLowerCase().contains(textoBusqueda) ||
                                                    vehiculo.getCliente().getId().toLowerCase().contains(textoBusqueda)
                                    )) ||
                                    vehiculo.getModelo().toLowerCase().contains(textoBusqueda) ||
                                    (vehiculo.getMembresia() != null &&
                                            vehiculo.getMembresia().getEstado().toLowerCase().contains(textoBusqueda))
                    )
                    .toList();
        } else {
            filtrados = vehiculoService.obtenerVehiculos().stream().filter(vehiculo -> {
                switch (criterioBusqueda) {
                    case "Placa":
                        return vehiculo.getPlaca().toLowerCase().contains(textoBusqueda);
                    case "Tipo":
                        return vehiculo.getClass().getSimpleName().toLowerCase().contains(textoBusqueda);
                    case "Cliente":
                        return vehiculo.getCliente() != null &&
                                (vehiculo.getCliente().getNombre().toLowerCase().contains(textoBusqueda) ||
                                        vehiculo.getCliente().getId().toLowerCase().contains(textoBusqueda));
                    default:
                        return false;
                }
            }).toList();
        }

        tablaVehiculos.setItems(FXCollections.observableArrayList(filtrados));
        actualizarEtiquetaResultados();
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

        Vehiculo vehiculoSeleccionado = tablaVehiculos.getSelectionModel().getSelectedItem();

        if(vehiculoSeleccionado != null){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/actualizarVehiculo.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

                ActualizarVehiculoController controller = fxmlLoader.getController();
                controller.setVehiculo(vehiculoSeleccionado);

                Stage stage = new Stage();
                stage.setTitle("Actualizar Vehiculo");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

            // Mostrar alerta si no hay selección
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Ninguna selección");
            alerta.setHeaderText("No se ha seleccionado ningún vehiculo");
            alerta.setContentText("Por favor, selecciona un vehiculo para editar.");
            alerta.showAndWait();

        }


    }

    @FXML
    void eliminarVehiculo(ActionEvent event) {

        Vehiculo vehiculoSeleccionado = tablaVehiculos.getSelectionModel().getSelectedItem();

        if (vehiculoSeleccionado != null) {
            // Confirmar la eliminación (opcional)
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar eliminación");
            alerta.setHeaderText("¿Estás seguro de eliminar este vehiculo?");
            alerta.setContentText("Vehiculo: " + vehiculoSeleccionado.getPlaca());

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Eliminar del servicio
                vehiculoService.eliminarVehiculo(vehiculoSeleccionado); // Asegúrate de tener este método

                // Eliminar del TableView
                tablaVehiculos.getItems().remove(vehiculoSeleccionado);
                actualizarEtiquetaResultados();
            }
        }else {
            // Mostrar alerta si no hay selección
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Ninguna selección");
            alerta.setHeaderText("No se ha seleccionado ningún vehiculo");
            alerta.setContentText("Por favor, selecciona un vehiculo para eliminar.");
            alerta.showAndWait();
        }

    }

    private void actualizarEtiquetaResultados() {
        int total = tablaVehiculos.getItems().size();
        lblResultados.setText(total + (total == 1 ? " resultado encontrado" : " resultados encontrados"));
    }

    @FXML
    void exportarResultados(ActionEvent event) {

    }

    @FXML
    void gestionarMembresia(ActionEvent event) {

    }

    @FXML
    void limpiarFiltros(ActionEvent event) {

        txtCliente.clear();
        cmbCriterioBusqueda.getSelectionModel().selectFirst();
        tablaVehiculos.setItems(FXCollections.observableArrayList(vehiculoService.obtenerVehiculos()));
        actualizarEtiquetaResultados();

    }

    @FXML
    void registrarNuevoVehiculo(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/registrarVehiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage stage = new Stage();
            stage.setTitle("Registrar vehiculo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void verDetallesVehiculo(ActionEvent event) {

    }

    @FXML
    void verHistorialIngresos(ActionEvent event) {

    }

}
