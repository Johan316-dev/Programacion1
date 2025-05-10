package com.example.controller;

import com.example.model.Cliente;
import com.example.service.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditarClienteController {

    @FXML
    private Label lblClienteNombre;

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

    private Cliente cliente;
    ClienteService clienteService = ClienteService.getInstancia();

    private String idAnterior;

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void cancelarEdicion(ActionEvent event) {

        // Obtener el Stage (ventana) desde el botón o cualquier otro componente
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); // Cierra solo esta ventana

    }

    @FXML
    void guardarCambios(ActionEvent event) {
        // Crear una copia temporal del cliente
        Cliente copia = new Cliente(
                txtCedula.getText(),
                txtNombre.getText(),
                txtTelefono.getText(),
                txtCorreo.getText()
        );

        boolean actualizado = clienteService.actualizarCliente(idAnterior, copia);

        Alert alerta;
        if (actualizado) {
            // Si se actualiza con éxito, aplicar los cambios al objeto original
            cliente.setNombre(copia.getNombre());
            cliente.setTelefono(copia.getTelefono());
            cliente.setCorreo(copia.getCorreo());
            cliente.setId(copia.getId());

            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Cliente actualizado");
            alerta.setContentText("El cliente se ha actualizado correctamente");
            alerta.showAndWait();
            ((Stage) txtNombre.getScene().getWindow()).close();
        } else {
            alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error al actualizar");
            alerta.setHeaderText("No se ha actualizado el cliente");
            alerta.setContentText("Verifica si ya existe otro cliente con esa cédula.");
            alerta.showAndWait();
        }


    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.idAnterior = cliente.getId(); // Guardar el ID original

        txtCedula.setText(cliente.getId());
        txtNombre.setText(cliente.getNombre());
        txtTelefono.setText(cliente.getTelefono());
        txtCorreo.setText(cliente.getCorreo());

        lblClienteNombre.setText("Editando a: " + cliente.getNombre());
    }

    public void setClienteService (ClienteService service){

        this.clienteService = service;

    }

}
