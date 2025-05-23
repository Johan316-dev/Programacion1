package com.example.controller;

import com.example.model.Parqueadero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatosParqueaderoController {

    @FXML
    private ComboBox<?> cmbRegimenTributario;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblUltimaActualizacion;

    @FXML
    private TextField txtCargoRepresentante;

    @FXML
    private TextField txtCedulaRepresentante;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtConsecutivoInicial;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtHorarioAtencion;

    @FXML
    private TextArea txtInformacionPago;

    @FXML
    private TextField txtMensajeAgradecimiento;

    @FXML
    private TextField txtNit;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtNotasLegales;

    @FXML
    private TextField txtPrefijoFactura;

    @FXML
    private TextField txtRepresentante;

    @FXML
    private TextField txtResolucionFacturacion;

    @FXML
    private TextField txtTelefonoPrincipal;

    private File logoSeleccionado = null;

    private HomeController homeController;

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }


    @FXML
    public void initialize() {

        cargarDatosEnCampos();
    }

    private void cargarDatosEnCampos() {
        Parqueadero datos = Parqueadero.getInstance();

        txtNombre.setText(datos.getNombre());
        txtNit.setText(datos.getNit());
        txtDireccion.setText(datos.getDireccion());
        txtCiudad.setText(datos.getCiudad());
        txtDepartamento.setText(datos.getDepartamento());

        txtRepresentante.setText(datos.getRepresentante());
        txtCedulaRepresentante.setText(datos.getCedulaRepresentante());
        txtCargoRepresentante.setText(datos.getCargoRepresentante());
        txtTelefonoPrincipal.setText(datos.getTelefonoPrincipal());
        txtCorreo.setText(datos.getCorreo());

        if (datos.getRutaLogo() != null) {
            Image logo = new Image(new File(datos.getRutaLogo()).toURI().toString());
            imgLogo.setImage(logo);
        }
    }

    @FXML
    void guardarCambios() {

        Parqueadero datos = Parqueadero.getInstance();

        datos.setNombre(txtNombre.getText());
        datos.setNit(txtNit.getText());
        datos.setDireccion(txtDireccion.getText());
        datos.setCiudad(txtCiudad.getText());
        datos.setDepartamento(txtDepartamento.getText());

        datos.setRepresentante(txtRepresentante.getText());
        datos.setCedulaRepresentante(txtCedulaRepresentante.getText());
        datos.setCargoRepresentante(txtCargoRepresentante.getText());
        datos.setTelefonoPrincipal(txtTelefonoPrincipal.getText());
        datos.setCorreo(txtCorreo.getText());

        if (logoSeleccionado != null) {
            datos.setRutaLogo(logoSeleccionado.getAbsolutePath());
        }

        if (homeController != null) {
            homeController.actualizarDatosParqueadero();
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        lblUltimaActualizacion.setText("Última actualización: " + LocalDateTime.now().format(formatter));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos Guardados");
        alert.setHeaderText("Confirmacion de Cambios");
        alert.setContentText("Los datos del parqueadero se han actualizado correctamente");
        alert.showAndWait();
        System.out.println("Datos actualizados en singleton.");
    }

    @FXML
    void cancelarCambios(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Descartar Cambios");
        alert.setHeaderText("¿Deseas descartar los cambios?");
        alert.setContentText("Los datos se restaurarán a la última versión guardada.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            cargarDatosEnCampos();

            //Cerrar ventana
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close(); // Cierra solo esta ventana


        }
    }



    @FXML
    void cambiarLogo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Logo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            logoSeleccionado = file;
            imgLogo.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    void vistaPrevia() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vista Previa");
        alert.setHeaderText("Vista previa de la factura");
        alert.setContentText("Aquí podría ir una vista previa de cómo se verá el encabezado de la factura con los datos ingresados.");
        alert.showAndWait();
    }

}
