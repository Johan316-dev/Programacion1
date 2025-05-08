package com.example.controller;

import com.example.model.Cliente;
import com.example.service.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Pattern;

public class CrearClienteController {

    @FXML
    private Label lblErrorCedula;

    @FXML
    private Label lblErrorCorreo;

    @FXML
    private Label lblErrorNombre;

    @FXML
    private Label lblErrorTelefono;

    @FXML
    private Label lblMensajeError;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private DialogPane dialogpane;

    ClienteService clienteService = ClienteService.getInstancia();


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    public void initialize() {
        Button closeButton = (Button) dialogpane.lookupButton(ButtonType.CLOSE);
        closeButton.addEventFilter(ActionEvent.ACTION, event -> {
            // Cerrar el diálogo manualmente
            dialogpane.getScene().getWindow().hide();
        });
        // Obtener el botón OK y añadirle un filtro de evento
        Button okButton = (Button) dialogpane.lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (!validarFormulario()) {
                event.consume(); // No cerrar el diálogo
            } else {
                Cliente cliente = new Cliente(
                        txtCedula.getText().trim(),
                        txtNombre.getText().trim(),
                        txtTelefono.getText().trim(),
                        txtCorreo.getText().trim());

                boolean agregado = clienteService.agregarCliente(cliente);
                if (!agregado) {
                    lblMensajeError.setText("Ya existe un cliente con esta cédula.");
                    lblMensajeError.setVisible(true);
                    event.consume();
                }else {
                    mostrarAlerta("Cliente agregado", "Se ha agregado correctamente.");
                    System.out.println("Lista de los clientes agregados: " );
                    for(Cliente c: clienteService.obtenerClientes()){
                        System.out.println("- " + c.getId() + " | " + c.getNombre());
                    }

                }
            }
        });


    }

    private boolean validarFormulario() {
        boolean valido = true;

        lblErrorCedula.setVisible(txtCedula.getText().trim().isEmpty());
        lblErrorNombre.setVisible(txtNombre.getText().trim().isEmpty());
        lblErrorTelefono.setVisible(txtTelefono.getText().trim().isEmpty());

        boolean correoValido = Pattern.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", txtCorreo.getText().trim());
        lblErrorCorreo.setVisible(txtCorreo.getText().trim().isEmpty() || !correoValido);


        if (lblErrorCedula.isVisible() || lblErrorNombre.isVisible()
                || lblErrorTelefono.isVisible() || lblErrorCorreo.isVisible()) {
            lblMensajeError.setVisible(true);
            valido = false;
        } else {
            lblMensajeError.setVisible(false);
        }

        return valido;
    }


}




