module com.toledo.control {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.toledo.control to javafx.fxml;
    exports com.toledo.control;
    exports com.toledo.control.controllers;
    opens com.toledo.control.controllers to javafx.fxml;
}