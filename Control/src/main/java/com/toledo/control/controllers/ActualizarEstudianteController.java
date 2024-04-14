package com.toledo.control.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.toledo.control.App;
import com.toledo.control.models.Student;
import com.toledo.control.models.Control;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ActualizarEstudianteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lastNameText;

    @FXML
    private Button actualizarButton;

    @FXML
    private ImageView regresarButton;

    @FXML
    private TextField nameText;

    @FXML
    private TextField matriculaText;

    @FXML
    private TableView<Student> TableEstudiantes;

    @FXML
    private TableColumn<Student, String> nameEstudiantesColumn;

    @FXML
    private TableColumn<Student, String> apellidoEstudiantesColumn;

    @FXML
    private TableColumn<Student, String> matriculaEstudiantesColumn;

    @FXML
    private Button verButton;

    @FXML
    private TextField matriculaBuscarText;

    @FXML
    void onMouseClickedActualizarButton(MouseEvent event) {
        Control registro = App.getControl();
        String name = nameText.getText();
        String apellido = lastNameText.getText();
        String matricula = matriculaText.getText();

        if (name.isEmpty() || apellido.isEmpty() || matricula.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Rellene los campos correctamente.");
            alert.showAndWait();
        } else {
            if (!registro.getEstudiantes().isEmpty()) {
                boolean estudianteEncontrado = false;
                for (Student student : registro.getEstudiantes()) {
                    if (name.equals(student.getName())) {
                        student.setFirstName(apellido);
                        student.setMatricula(matricula);
                        estudianteEncontrado = true;
                        nameText.clear();
                        lastNameText.clear();
                        matriculaText.clear();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("¡Los datos sean actualizado correctamente!.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("No exite el estudiante , no se puede actualizar.");
                        alert.showAndWait();
                    }
                }
                if (estudianteEncontrado) {
                    TableEstudiantes.getItems().clear();
                    TableEstudiantes.getItems().addAll(registro.getEstudiantes());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No se ha podido actualizar, la lista se encuentra vacia.");
                alert.showAndWait();
            }
        }
    }

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
        Control registro = App.getControl();
        if (!registro.getEstudiantes().isEmpty()) {
            TableEstudiantes.getItems().clear();
            TableEstudiantes.getItems().addAll(registro.getEstudiantes());

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("La lista esta vacia.");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        matriculaBuscarText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buscarEstudiantes(newValue);
            }
        });
        configurarColumnas();
    }

    private void buscarEstudiantes(String matricula) {
        Control registro = App.getControl();
        ObservableList<Student> estudiantesFiltrados = FXCollections.observableArrayList();
        for (Student estudiante : registro.getEstudiantes()) {
            if (estudiante.getMatricula().contains(matricula)) {
                estudiantesFiltrados.add(estudiante);
            }
        }
        TableEstudiantes.setItems(estudiantesFiltrados);
        TableEstudiantes.setItems(estudiantesFiltrados);
        TableEstudiantes.setItems(estudiantesFiltrados);
    }

    public void configurarColumnas() {
        nameEstudiantesColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        apellidoEstudiantesColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        matriculaEstudiantesColumn.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
    }

    private void cerrarVentana() {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        stage.close();
    }
}
