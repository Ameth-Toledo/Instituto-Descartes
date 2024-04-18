package com.toledo.control.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.toledo.control.models.Student;
import com.toledo.control.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.toledo.control.models.Control;

public class GuardarEstudianteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button verButton;

    @FXML
    private ImageView regresarButton;

    @FXML
    private TextField nameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField matriculaText;

    private Control control = App.getControl();

    @FXML
    void onMouseClickedRegresarButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/toledo/control/imagenes/DescartesLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void onMouseClickedVerButton(MouseEvent event) {
        if (control != null) {
            String name = nameText.getText();
            String firstName = lastNameText.getText();
            String matricula = matriculaText.getText();
            Student student = new Student(name, firstName, matricula);

            try {
                if (control.getSQLite() != null) {
                    control.getSQLite().saveStudent(student);
                    mostrarAlerta("Estudiante agregado exitosamente");
                    System.out.println("Estudiante guardado exitosamente en todas las bases de datos.");
                }
                if (control.getAccess() != null) {
                    control.getAccess().saveStudent(student);
                    System.out.println("Estudiante guardado exitosamente en todas las bases de datos.");
                }
                if (control.getMySQL() != null) {
                    control.getMySQL().saveStudent(student);
                    System.out.println("Estudiante guardado exitosamente en todas las bases de datos.");
                }
            } catch (Exception e) {
                mostrarAlertaError("Ocurrio un error al guardar el estudiante");
                System.err.println("Error al guardar el estudiante: " + e.getMessage());
            }
        } else {
            System.err.println("La instancia de Registro no ha sido inicializada correctamente.");
        }
    }

    @FXML
    void initialize() {
    }

    public void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        stage.close();
    }
}
