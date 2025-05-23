package com.example.controller;

import com.example.model.Cliente;
import com.example.model.Membresia;
import com.example.model.Vehiculo;
import com.example.service.ClienteService;
import com.example.service.VehiculoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.ZoneId;
import java.util.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionarMembresiaController {

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private Label lblCedulaCliente;

    @FXML
    private Label lblColor;

    @FXML
    private Label lblCorreoCliente;

    @FXML
    private Label lblDescuento;

    @FXML
    private Label lblEstadoMembresia;

    @FXML
    private Label lblFechaFin;

    @FXML
    private Label lblMensajeError;

    @FXML
    private Label lblModelo;

    @FXML
    private Label lblNombreCliente;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblPlanSeleccionado;

    @FXML
    private Label lblPrecio1Anio;

    @FXML
    private Label lblPrecio1Mes;

    @FXML
    private Label lblPrecio3Meses;

    @FXML
    private Label lblPrecioBase;

    @FXML
    private Label lblTelefonoCliente;

    @FXML
    private Label lblTipo;

    @FXML
    private Label lblTotalPagar;

    @FXML
    private VBox panelDatosPago;

    @FXML
    private RadioButton rbEfectivo;

    @FXML
    private RadioButton rbPlan1Anio;

    @FXML
    private RadioButton rbPlan1Mes;

    @FXML
    private RadioButton rbPlan3Meses;

    @FXML
    private RadioButton rbTarjeta;

    @FXML
    private RadioButton rbTransferencia;

    @FXML
    private VBox tarjeta1Anio;

    @FXML
    private VBox tarjeta1Mes;

    @FXML
    private VBox tarjeta3Meses;

    @FXML
    private TextArea txtObservaciones;

    @FXML
    private TextField txtReferenciaPago;

    @FXML
    private ToggleGroup grupoMetodoPago;

    @FXML
    private ToggleGroup grupoPlanes;

    /**
     * Singleton
     */
    ClienteService clienteService = ClienteService.getInstancia();
    VehiculoService vehiculoService = VehiculoService.getInstancia();
    //---------------------------------------------//

    private Vehiculo vehiculoSeleccionado; // asignado al iniciar la vista
    private ActualizarVehiculoController actualizarVehiculoController;




    private final int PRECIO_1_MES = 100_000;
    private final int PRECIO_3_MESES = 300_000;
    private final int PRECIO_1_ANIO = 1_200_000;
    private final int DESCUENTO_3_MESES = 15_000;
    private final int DESCUENTO_1_ANIO = 180_000;

    public void setActualizarVehiculoController(ActualizarVehiculoController controller) {
        this.actualizarVehiculoController = controller;
    }


    @FXML
    public void initialize() {

        ToggleGroup grupoPlanes = new ToggleGroup();

        rbPlan1Mes.setToggleGroup(grupoPlanes);
        rbPlan1Anio.setToggleGroup(grupoPlanes);
        rbPlan3Meses.setToggleGroup(grupoPlanes);

        rbPlan1Mes.setSelected(true);

        ToggleGroup grupoMetodoPago = new ToggleGroup();

        rbEfectivo.setToggleGroup(grupoMetodoPago);
        rbTarjeta.setToggleGroup(grupoMetodoPago);
        rbTransferencia.setToggleGroup(grupoMetodoPago);

        rbEfectivo.setSelected(true);


        // Configurar evento para cambiar resumen al cambiar fecha
        dpFechaInicio.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (rbPlan1Mes.isSelected()) {
                seleccionarPlan1Mes();
            } else if (rbPlan3Meses.isSelected()) {
                seleccionarPlan3Meses();
            } else if (rbPlan1Anio.isSelected()) {
                seleccionarPlan1Anio();
            }
        });

        seleccionarPlan1Mes(); // Selección por defecto


        txtReferenciaPago.textProperty().addListener((obs, oldVal, newVal) -> {
            lblMensajeError.setVisible(false);
        });
    }

    private void actualizarResumenPlan(String nombrePlan, int mesesDuracion, int precioBase, int descuento) {
        lblPlanSeleccionado.setText(nombrePlan);

        LocalDate fechaInicio = dpFechaInicio.getValue();
        if (fechaInicio == null) {
            fechaInicio = LocalDate.now();
            dpFechaInicio.setValue(fechaInicio);
        }

        LocalDate fechaFin = fechaInicio.plusMonths(mesesDuracion);
        lblFechaFin.setText(fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        lblPrecioBase.setText(String.format("%,d", precioBase));
        lblDescuento.setText(String.format("%,d (%.0f%%)", descuento, (double) descuento / precioBase * 100));
        lblTotalPagar.setText(String.format("%,d", precioBase - descuento));
    }


    public void setVehiculo(Vehiculo vehiculo) {

        this.vehiculoSeleccionado = vehiculo;

        Cliente cliente = vehiculo.getCliente();
        if (cliente != null) {
            lblNombreCliente.setText(cliente.getNombre());
            lblCedulaCliente.setText(cliente.getId());
            lblTelefonoCliente.setText(cliente.getTelefono());
            lblCorreoCliente.setText(cliente.getCorreo());
        }

        lblPlaca.setText(vehiculo.getPlaca());
        lblTipo.setText(vehiculo.getClass().getSimpleName()); // Automovil, Moto, etc.
        lblModelo.setText(vehiculo.getModelo());
        lblColor.setText(vehiculo.getColor());
    }

    public void cargarDatosMembresia(Vehiculo vehiculo) {
        if (vehiculo.getMembresia() != null &&
                "Activa".equalsIgnoreCase(vehiculo.getMembresia().getEstado())) {

            lblEstadoMembresia.setText("Membresía activa");
            lblEstadoMembresia.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-padding: 5px 10px; -fx-background-radius: 3px;");

        } else {
            lblEstadoMembresia.setText("Sin membresía activa");
            lblEstadoMembresia.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 5px 10px; -fx-background-radius: 3px;");
        }
    }



    @FXML
    void cancelarOperacion(ActionEvent event) {

        // Obtener el Stage (ventana) desde el botón o cualquier otro componente
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); // Cierra solo esta ventana

    }

    private void mostrarError(String mensaje) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setVisible(true);
    }

    private void mostrarMensaje(String mensaje) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
        lblMensajeError.setVisible(true);
    }


    @FXML
    void confirmarPago(ActionEvent event) {

        lblMensajeError.setVisible(false);

        String metodoPago;
        if (rbEfectivo.isSelected()) {
            metodoPago = "Efectivo";
        } else if (rbTarjeta.isSelected()) {
            metodoPago = "Tarjeta";
        } else if (rbTransferencia.isSelected()) {
            metodoPago = "Transferencia";
        } else {
            metodoPago = "";
        }

        String referencia = txtReferenciaPago.getText().trim();
        String observaciones = txtObservaciones.getText().trim();
        LocalDate fechaInicio = dpFechaInicio.getValue();

        if (fechaInicio == null || metodoPago.isEmpty()) {
            mostrarError("Debe seleccionar una fecha de inicio y un método de pago.");
            return;
        }

        if (!metodoPago.equals("Efectivo") && referencia.isEmpty()) {
            mostrarError("Debe ingresar una referencia de pago para tarjeta o transferencia.");
            return;
        }


        // Obtener plan seleccionado
        String plan = lblPlanSeleccionado.getText();
        String fechaFin = lblFechaFin.getText();
        String precioBase = lblPrecioBase.getText();
        String descuento = lblDescuento.getText();
        String total = lblTotalPagar.getText();
        System.out.println("Texto en lblTotalPagar: " + lblTotalPagar.getText());


        //CREAR MEMBRESIA
        String tipoPlan = lblPlanSeleccionado.getText();
        Double costoTotal = Double.parseDouble(lblTotalPagar.getText().replace(".", ""));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaFinLocal = LocalDate.parse(lblFechaFin.getText(), formatter);

        String estado = "Activa";

        // Convertir LocalDate a Date
        Date fechaInicioDate = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFinDate = Date.from(fechaFinLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Crear la membresía
        Membresia membresia = new Membresia(tipoPlan, fechaInicioDate, fechaFinDate, costoTotal, estado);

        // Asociar al vehículo actual
        if (vehiculoSeleccionado != null) {
            vehiculoSeleccionado.setMembresia(membresia);
        } else {
            mostrarError("Error interno: vehículo no asignado.");
            return;
        }


        // 👉 Actualiza la vista si hay referencia disponible
        if (actualizarVehiculoController != null) {
            actualizarVehiculoController.actualizarVistaMembresia(membresia);
        }


        // Aquí puedes guardar los datos en el sistema, base de datos, etc.
        System.out.println("---- DATOS DE PAGO ----");
        System.out.println("Plan: " + plan);
        System.out.println("Inicio: " + fechaInicio);
        System.out.println("Fin: " + fechaFin);
        System.out.println("Método de Pago: " + metodoPago);
        System.out.println("Referencia: " + referencia);
        System.out.println("Observaciones: " + observaciones);
        System.out.println("Precio Base: " + precioBase);
        System.out.println("Descuento: " + descuento);
        System.out.println("Total: " + total);

        lblEstadoMembresia.setText("Membresía activa");
        lblEstadoMembresia.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-padding: 5px 10px; -fx-background-radius: 3px;");


        mostrarMensaje("Pago registrado con éxito. Puede generar la factura.");

    }




    @FXML
    void generarFactura(ActionEvent event) {

        // Este método puede crear un archivo PDF, mostrar un resumen, etc.
        mostrarMensaje("Factura generada exitosamente.");

    }

    @FXML
    void seleccionarPlan1Anio() {

        rbPlan1Anio.setSelected(true);
        actualizarResumenPlan("Plan Premium (1 año)", 12, PRECIO_1_ANIO, DESCUENTO_1_ANIO);

    }

    @FXML
    void seleccionarPlan1Mes() {

        rbPlan1Mes.setSelected(true);
        actualizarResumenPlan("Plan Basico (1 Mes)", 1, PRECIO_1_MES, 0);

    }

    @FXML
    void seleccionarPlan3Meses() {

        rbPlan3Meses.setSelected(true);
        actualizarResumenPlan("Plan Estandar (3 Meses)", 3, PRECIO_3_MESES, DESCUENTO_3_MESES);

    }

}
