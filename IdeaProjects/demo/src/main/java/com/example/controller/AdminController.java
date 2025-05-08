package com.example.controller;

import com.example.service.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;

public class AdminController {

    private final AdminService adminService = new AdminService();

    @FXML
    private TextField cedulaField;

    @FXML
    private Button loginButton;

    @FXML
    private Label mensajeError;

    @FXML
    private TextField nombreField;

    @FXML
    private Text errorText; // Texto para mostrar errores

    @FXML
    void Login(ActionEvent event) {

        String id = cedulaField.getText().trim();
        String nombre = nombreField.getText().trim();

        if (adminService.validarLogin(id, nombre)) {
            System.out.println("Login exitoso");
            // Aquí podrías cambiar de escena o abrir un nuevo panel
        } else {
            System.out.println("Login erroneo");
        }
    }

}



