package com.example.controller;

import com.example.model.HelloApplication;
import com.example.service.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class AdminController {

    private final AdminService adminService = new AdminService();

    @FXML
    private PasswordField cedulaField;

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

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/view/home.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                HomeController homeController = fxmlLoader.getController();

                homeController.iniciarReloj();

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                Stage newStage = new Stage();
                newStage.setTitle("Home Parqueadero");
                newStage.setScene(scene);
                newStage.setX(screenBounds.getMinX());
                newStage.setY(screenBounds.getMinY());
                newStage.setWidth(screenBounds.getWidth());
                newStage.setHeight(screenBounds.getHeight());
                newStage.show();



                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                errorText.setText("Error al cargar la interfaz.");
                errorText.setStyle("-fx-fill: red;");
            }
        } else {
            errorText.setText("Usuario o cedula incorrectos");
            errorText.setStyle("-fx-fill: red;");
        }
    }


}



