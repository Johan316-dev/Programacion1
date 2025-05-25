package com.example.controller;

import com.example.model.ConfiguracionParqueadero;
import com.example.model.HelloApplication;
import com.example.model.Tarifa;
import com.example.model.VehiculoTemporal;
import com.example.service.VehiculoTemporalService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDateTime;


public class VehiculosActivosController {

    @FXML
    private ComboBox<String> cmbFiltroTipo;

    @FXML
    private TableColumn<VehiculoTemporal, String> colAcciones;

    @FXML
    private TableColumn<VehiculoTemporal, String> colFechaIngreso;

    @FXML
    private TableColumn<VehiculoTemporal, String> colHoraIngreso;

    @FXML
    private TableColumn<VehiculoTemporal, String> colPlacaActivos;

    @FXML
    private TableColumn<VehiculoTemporal, String> colPuestoActivos;

    @FXML
    private TableColumn<VehiculoTemporal, String> colTarifaActual;

    @FXML
    private TableColumn<VehiculoTemporal, String> colTicket;

    @FXML
    private TableColumn<VehiculoTemporal, String> colTiempoTranscurrido;

    @FXML
    private TableColumn<VehiculoTemporal, String> colTipoActivos;

    @FXML
    private Label lblEstadoSistema;

    @FXML
    private Label lblFechaHoraActual;

    @FXML
    private Label lblTotalVehiculosActivos;

    @FXML
    private Label lblUltimaSalida;

    @FXML
    private Label lblUltimoIngreso;

    @FXML
    private TableView<VehiculoTemporal> tablaVehiculosActivos;

    @FXML
    private TextField txtFiltroPlaca;

    VehiculoTemporalService vehiculoTemporalService = VehiculoTemporalService.getInstancia();



    @FXML
    public void initialize() {

        // Usar java.time.Duration explícitamente donde se necesita para cálculos de tiempo
        colTiempoTranscurrido.setCellValueFactory(data -> {
            java.time.Duration dur = java.time.Duration.between(data.getValue().getFechaHoraIngreso(), LocalDateTime.now());
            long horas = dur.toHours();
            long minutos = dur.toMinutesPart(); // Correcto: obtiene la parte de los minutos
            long segundos = dur.toSecondsPart(); // Correcto: obtiene la parte de los segundos
            return new SimpleStringProperty(String.format("%dh %dm %ds", horas, minutos, segundos));
        });

        // Usar javafx.util.Duration explícitamente para Timeline
        Timeline timer = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), event -> {
                    tablaVehiculosActivos.refresh(); // Refresca la tabla
                })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        lblUltimoIngreso.setText("-");
        lblUltimaSalida.setText("-");

        cargarVehiculosActivos();
        cargarColumnas();
        actualizarFechaHoraActual();

    }

    private void cargarVehiculosActivos() {

        ObservableList<VehiculoTemporal> lista = FXCollections.observableArrayList(
                vehiculoTemporalService.obtenerVehiculos()
        );
        tablaVehiculosActivos.setItems(lista);
        lblTotalVehiculosActivos.setText("Total de vehículos: " + lista.size());

        if (!lista.isEmpty()) {
            DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            VehiculoTemporal ultimo = lista.get(lista.size() - 1);
            //VehiculoTemporal salida = lista.get(lista.size() - 2);
            //lblUltimaSalida.setText(salida.getPlaca() + " - " + salida.getFechaHoraIngreso().format(horaFormatter));
            lblUltimoIngreso.setText(ultimo.getPlaca() + " - " + ultimo.getFechaHoraIngreso().format(horaFormatter));
        }
    }

    private void actualizarFechaHoraActual() {
        lblFechaHoraActual.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm a")));
    }



    private void cargarColumnas() {
        //colTicket.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId() + ""));
        colPlacaActivos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlaca()));
        colTipoActivos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoVehiculo()));
        //colPuestoActivos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPuesto() != null ? data.getValue().getPuesto() : "-"));

        colFechaIngreso.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFechaHoraIngreso().toLocalDate().toString()));

        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        colHoraIngreso.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFechaHoraIngreso().toLocalTime().format(horaFormatter)));


        colTarifaActual.setCellValueFactory(data -> {
            VehiculoTemporal vehiculo = data.getValue();

            // Suponiendo que tienes un método para obtener la tarifa por hora según el tipo
            double tarifaPorHora = obtenerTarifaPorHora(vehiculo.getTipoVehiculo());

            // Se cobra mínimo una hora
            return new SimpleStringProperty(String.format("$ %,d", (int) tarifaPorHora));
        });

    }

    private double obtenerTarifaPorHora(String tipoVehiculo) {

        Tarifa tarifa = ConfiguracionParqueadero.getInstancia().getTarifa();

        switch (tipoVehiculo.toLowerCase()) {
            case "automóvil":
                return tarifa.getTarifaHoraAuto();
            case "moto":
                return tarifa.getTarifaHoraMoto();
            case "camión":
                return tarifa.getTarifaHoraCamion() ;
            default:
                return 0;
        }
    }



    @FXML
    void actualizarListaVehiculos(ActionEvent event) {

        ObservableList<VehiculoTemporal> lista = FXCollections.observableArrayList(
                vehiculoTemporalService.obtenerVehiculos()
        );
        tablaVehiculosActivos.setItems(lista);
        lblTotalVehiculosActivos.setText("Total de vehículos: " + lista.size());

        txtFiltroPlaca.clear();
        cmbFiltroTipo.getSelectionModel().selectFirst();
        tablaVehiculosActivos.setItems(FXCollections.observableArrayList(vehiculoTemporalService.obtenerVehiculos()));


    }

    @FXML
    void filtrarVehiculos(ActionEvent event) {
        String criterioBusqueda = cmbFiltroTipo.getValue(); // Tipo de vehículo o "Todos"
        String textoBusqueda = txtFiltroPlaca.getText().toLowerCase().trim();

        List<VehiculoTemporal> filtrados = vehiculoTemporalService.obtenerVehiculos().stream()
                .filter(vehiculo -> {
                    boolean coincidePlaca = vehiculo.getPlaca().toLowerCase().contains(textoBusqueda);
                    boolean coincideTipo = vehiculo.getTipoVehiculo().toLowerCase().contains(textoBusqueda);

                    boolean coincideTexto = coincidePlaca || coincideTipo;

                    if (criterioBusqueda == null || criterioBusqueda.equals("Todos")) {
                        return coincideTexto;
                    } else {
                        return vehiculo.getTipoVehiculo().equalsIgnoreCase(criterioBusqueda) && coincideTexto;
                    }
                })
                .toList();

        tablaVehiculosActivos.getItems().setAll(filtrados);
        lblTotalVehiculosActivos.setText("Total de vehículos: " + filtrados.size());
    }


    @FXML
    void procesarSalidaSeleccionado(ActionEvent event) {

        VehiculoTemporal vehiculoSeleccionado = tablaVehiculosActivos.getSelectionModel().getSelectedItem();

        if (vehiculoSeleccionado != null){

            // Obtener el Stage (ventana) desde el botón o cualquier otro componente
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close(); // Cierra solo esta ventana

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/registrarSalida.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

                RegistrarSalidaController controller = fxmlLoader.getController();
                controller.setPlaca( vehiculoSeleccionado.getPlaca());

                //Stage stage = new Stage();
                stage.setTitle("Salida Vehiculo");
                stage.setMaximized(false);
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
            alerta.setContentText("Por favor, selecciona un vehiculo.");
            alerta.showAndWait();


        }

    }

}
