package com.example.controller;

import com.example.model.Cliente;
import com.example.model.HelloApplication;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BuscarClienteController {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnVehiculosCliente;

    @FXML
    private ComboBox<String> cmbCriterioBusqueda;

    @FXML
    private TableColumn<Cliente, String> colCedula;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colVehiculos;

    @FXML
    private Label lblCedulaDetalle;

    @FXML
    private Label lblCorreoDetalle;

    @FXML
    private Label lblNombreDetalle;

    @FXML
    private Label lblResultados;

    @FXML
    private Label lblTelefonoDetalle;

    @FXML
    private VBox panelDetalles;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TextField txtBusqueda;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//
    private Cliente clienteSeleccionado;

    public void refrescarTablaClientes() {
        tablaClientes.refresh();
    }


    @FXML
    public void initialize() {

        cmbCriterioBusqueda.getSelectionModel().selectFirst();

        // Asociar columnas a propiedades
        colCedula.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colVehiculos.setCellValueFactory(cellData -> {
            int cantidad = cellData.getValue().getVehiculos().size();
            return new SimpleStringProperty(String.valueOf(cantidad));
        });

        // Cargar datos
        tablaClientes.setItems(FXCollections.observableArrayList(clienteService.obtenerClientes()));
        actualizarEtiquetaResultados();

        tablaClientes.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Cliente clienteSeleccionado = row.getItem();
                    abrirVentanaEdicion(clienteSeleccionado);
                }
            });
            return row;
        });
    }

    private void abrirVentanaEdicion(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/editarCliente.fxml"));
            Parent root = loader.load();

            // Pasar el cliente al controlador de edición
            EditarClienteController controller = loader.getController();
            controller.setCliente(cliente);
            controller.setClienteService(clienteService); // si es necesario

            Stage stage = new Stage();
            stage.setTitle("Editar Cliente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Refrescar la tabla tras cerrar la ventana
            tablaClientes.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarEtiquetaResultados() {
        int total = tablaClientes.getItems().size();
        lblResultados.setText(total + (total == 1 ? " resultado encontrado" : " resultados encontrados"));
    }



    @FXML
    private void buscarClientes() {
        String criterio = cmbCriterioBusqueda.getValue();
        String texto = txtBusqueda.getText().trim().toLowerCase();

        List<Cliente> filtrados;

        if (criterio == null || criterio.equals("Todos")) {
            filtrados = clienteService.obtenerClientes().stream()
                    .filter(cliente ->
                            cliente.getId().toLowerCase().contains(texto) ||
                                    cliente.getNombre().toLowerCase().contains(texto) ||
                                    cliente.getCorreo().toLowerCase().contains(texto) ||
                                    cliente.getTelefono().toLowerCase().contains(texto)
                    ).toList();
        } else {
            filtrados = clienteService.obtenerClientes().stream().filter(cliente -> {
                switch (criterio) {
                    case "Cédula":
                        return cliente.getId().toLowerCase().contains(texto);
                    case "Nombre":
                        return cliente.getNombre().toLowerCase().contains(texto);
                    case "Teléfono":
                        return cliente.getTelefono().toLowerCase().contains(texto);
                    default:
                        return false;
                }
            }).toList();
        }

        tablaClientes.setItems(FXCollections.observableArrayList(filtrados));
        actualizarEtiquetaResultados();
    }

    public void eliminar(ActionEvent actionEvent) {

        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            // Confirmar la eliminación (opcional)
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar eliminación");
            alerta.setHeaderText("¿Estás seguro de eliminar este cliente?");
            alerta.setContentText("Cliente: " + seleccionado.getNombre());

            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Eliminar del servicio
                clienteService.eliminarCliente(seleccionado); // Asegúrate de tener este método

                // Eliminar del TableView
                tablaClientes.getItems().remove(seleccionado);
                actualizarEtiquetaResultados();
            }
        } else {
            // Mostrar alerta si no hay selección
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Ninguna selección");
            alerta.setHeaderText("No se ha seleccionado ningún cliente");
            alerta.setContentText("Por favor, selecciona un cliente para eliminar.");
            alerta.showAndWait();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    void cerrarVista(ActionEvent event) {

        // Obtener el Stage (ventana) desde el botón o cualquier otro componente
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); // Cierra solo esta ventana

    }



    @FXML
    void limpiarBusqueda(ActionEvent event) {
        txtBusqueda.clear();
        cmbCriterioBusqueda.getSelectionModel().selectFirst(); // Selecciona "Todos"
        tablaClientes.setItems(FXCollections.observableArrayList(clienteService.obtenerClientes()));
        actualizarEtiquetaResultados();

    }

    @FXML
    public void verVehiculos(ActionEvent event) {

        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if(seleccionado != null){
            System.out.println("Cliente seleccionado: " + seleccionado.getNombre());

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/vehiculosCliente.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

                VehiculoClienteController controller = fxmlLoader.getController();

                controller.setCliente(seleccionado);
                controller.cargarVehiculosCliente(seleccionado);


                Stage stage = new Stage();
                stage.setTitle("Detalle vehiculo");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            // Mostrar alerta si no hay selección
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Ninguna selección");
            alerta.setHeaderText("No se ha seleccionado ningún cliente");
            alerta.setContentText("Por favor, selecciona un cliente.");
            alerta.showAndWait();
        }

    }
}
