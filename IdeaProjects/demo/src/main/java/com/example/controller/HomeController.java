package com.example.controller;

import com.example.model.*;
import com.example.service.MembresiaService;
import com.example.service.PagoService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomeController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCrearCliente;

    @FXML
    private TableColumn<Membresia, String> colCliente;

    @FXML
    private TableColumn<Membresia, String> colFechaVencimiento;

    @FXML
    private TableColumn<Membresia, Double> colPlaca;

    @FXML
    private TableColumn<Membresia, String> colVehiculo;

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
    private ImageView imgHome;

    @FXML
    private TableView<Membresia> tablaMembresiasPorVencer;

    MembresiaService membresiaService = MembresiaService.getInstancia();
    PagoService pagoService = PagoService.getInstancia();

    @FXML
    public void initialize() {

        ConfiguracionParqueadero.getInstancia().cuposActualizadosProperty().addListener((obs, oldVal, newVal) -> {
            actualizarTarjetasCupos(); // cuando se notifique un cambio
        });


        actualizarTarjetasCupos();
    }



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

    public void actualizarDatosParqueadero() {
        Parqueadero datos = Parqueadero.getInstance();

        nombreParqueaderoLabel.setText(datos.getNombre());

        if (datos.getRutaLogo() != null) {
            File logoFile = new File(datos.getRutaLogo());
            if (logoFile.exists()) {
                imgHome.setImage(new Image(logoFile.toURI().toString()));
            }
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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/buscarVehiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage stage = new Stage();
            stage.setTitle("Buscar Vehiculo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void mostrarConfigurarEspacios(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/CuposTarifa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage stage = new Stage();
            stage.setTitle("Cupos y Tarifas");
            stage.setMaximized(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void actualizarTarjetasCupos() {

        Cupo cupo = ConfiguracionParqueadero.getInstancia().getCupo();

        espaciosMotosLabel.setText(
                cupo.getOcupadoMoto() + "/" + cupo.getMoto()
        );

        espaciosAutomovilesLabel.setText(
                cupo.getOcupadoAutomovil() + "/" + cupo.getAutomovil()
        );

        espaciosCamionesLabel.setText(
                cupo.getOcupadoCamion() + "/" + cupo.getCamion()
        );
    }




    @FXML
    void mostrarDatosParqueadero(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/datosParqueadero.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            DatosParqueaderoController controller = fxmlLoader.getController();
            controller.setHomeController(this);

            Stage stage = new Stage();
            stage.setTitle("Datos Parqueadero");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void mostrarGenerarFactura(ActionEvent event) {

    }

    @FXML
    void mostrarHistorialPagos(ActionEvent event) {

        List<Pago> pagos = pagoService.getHistorial();
        String ruta = "C:/REPORTES/pagos.pdf";
        ReporteHistorialPagos.generarPDF(pagos, ruta);

        // Mostrar alerta si no hay selección
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText("Reporte generado");
        alerta.setContentText("El pdf ha sido generado correctamente");
        alerta.showAndWait();

        // Intentar abrir el PDF
        try {
            File file = new File(ruta);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                mostrarAlerta("Error", "El archivo PDF no fue encontrado.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo abrir el PDF: " + e.getMessage());
        }

    }

    public void mostrarAlerta(String title, String message) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText("ERROT");
        alerta.setContentText("NO HAY PDF");
        alerta.showAndWait();
    }

    @FXML
    void mostrarMembresiasActivas(ActionEvent event) {

    }

    @FXML
    void mostrarRegistrarIngreso(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/registrarIngreso.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            RegistrarIngresoController controller = fxmlLoader.getController();
            controller.iniciarReloj();

            Stage stage = new Stage();
            stage.setTitle("Registrar Ingreso");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mostrarRegistrarMembresia(ActionEvent event) {

    }

    @FXML
    void mostrarRegistrarSalida(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/registrarSalida.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage stage = new Stage();
            stage.setTitle("Salida Vehiculo");
            stage.setMaximized(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    void mostrarVehiculosActivos(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/vehiculosActivos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            Stage stage = new Stage();
            stage.setTitle("Vehiculos Activos");
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
