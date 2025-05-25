module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires kernel;
    requires layout;

    opens com.example.controller to javafx.fxml;
    exports com.example.model;
    exports com.example.controller;
}
