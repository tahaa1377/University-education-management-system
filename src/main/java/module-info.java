module com.example.management {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.management to javafx.fxml;
    exports com.example.management;
    exports com.example.management.model;
    opens com.example.management.model to javafx.fxml;
    exports com.example.management.controller;
    opens com.example.management.controller to javafx.fxml;
}