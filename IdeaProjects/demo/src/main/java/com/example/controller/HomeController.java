package com.example.controller;

import com.example.model.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCrearCliente;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colFechaVencimiento;

    @FXML
    private TableColumn<?, ?> colPlaca;

    @FXML
    private TableColumn<?, ?> colVehiculo;

    @FXML
    private StackPane contenedorPrincipal;

    @FXML
    private Label espaciosAutomovilesLabel;

    @FXML
    private Label espaciosCamionesLabel;

    @FXML
    private Label espaciosMotosLabel;

    @FXML
    private Label fechaHoraLabel;

    @FXML
    private Label nombreParqueaderoLabel;

    @FXML
    private VBox panelBienvenida;

    @FXML
    private Label statusLabel;

    @FXML
    private TableView<?> tablaMembresiasPorVencer;

    @FXML
    void cerrarSesion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");

    public void iniciarReloj() {
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalDateTime ahora = LocalDateTime.now();
            fechaHoraLabel.setText("Sesión iniciada: " + ahora.format(formatter));
        }));
        reloj.setCycleCount(Timeline.INDEFINITE); // Para que se repita indefinidamente
        reloj.play();
    }



    @FXML
    void mostrarActualizarCliente(ActionEvent event) {

    }

    @FXML
    void mostrarActualizarVehiculo(ActionEvent event) {

    }

    @FXML
    void mostrarAnadirCliente(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/createCliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            Stage stage = new Stage();
            stage.setTitle("Añadir Cliente");
            stage.setMaximized(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mostrarBuscarCliente(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/buscarCliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage stage = new Stage();
            stage.setTitle("Añadir Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mostrarBuscarVehiculo(ActionEvent event) {

    }

    @FXML
    void mostrarConfigurarEspacios(ActionEvent event) {

    }

    @FXML
    void mostrarConfigurarTarifas(ActionEvent event) {

    }

    @FXML
    void mostrarDatosParqueadero(ActionEvent event) {

    }

    @FXML
    void mostrarEliminarCliente(ActionEvent event) {

    }

    @FXML
    void mostrarGenerarFactura(ActionEvent event) {

    }

    @FXML
    void mostrarHistorialPagos(ActionEvent event) {

    }

    @FXML
    void mostrarMembresiasActivas(ActionEvent event) {

    }

    @FXML
    void mostrarRegistrarIngreso(ActionEvent event) {

    }

    @FXML
    void mostrarRegistrarMembresia(ActionEvent event) {

    }

    @FXML
    void mostrarRegistrarSalida(ActionEvent event) {

    }

    @FXML
    void mostrarRegistrarVehiculo(ActionEvent event) {

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
    void mostrarTotalIngresos(ActionEvent event) {

    }

    @FXML
    void mostrarVehiculosActuales(ActionEvent event) {

    }

    @FXML
    void mostrarVehiculosCliente(ActionEvent event) {

    }

    @FXML
    void mostrarVehiculosPorCliente(ActionEvent event) {

    }

}
