package com.toledo.control.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.toledo.control.App;
import com.toledo.control.models.SQLite;
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

    private Control control = App.getControl();

    @FXML
    void onMouseClickedActualizarButton(MouseEvent event) {
        String name = nameText.getText();
        String apellido = lastNameText.getText();
        String matricula = matriculaText.getText();

        if (name.isEmpty() || apellido.isEmpty() || matricula.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Rellene los campos correctamente.");
            alert.showAndWait();
        } else {
            boolean estudianteEncontrado = false;
            for (Student student : TableEstudiantes.getItems()) {
                if (matricula.equals(student.getMatricula())) {
                    student.setName(name);
                    student.setFirstName(apellido);
                    student.setMatricula(matricula);
                    control.update(student);
                    estudianteEncontrado = true;
                    nameText.clear();
                    lastNameText.clear();
                    matriculaText.clear();
                    break;
                }
            }
            if (!estudianteEncontrado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No existe el estudiante, no se puede actualizar.");
                alert.showAndWait();
            } else {
                TableEstudiantes.getItems().clear();
                TableEstudiantes.getItems().addAll(control.getMySQL().getEstudiantes());
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
        SQLite sqLite=new SQLite();
        if (control !=null && control.getMySQL()!=null && control.getMySQL().getEstudiantes()!=null) {
            TableEstudiantes.getItems().addAll(control.getMySQL().getEstudiantes());

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("La lista se encuentra vacia.");
            alert.showAndWait();

        }
    }

    @FXML
    void initialize() {
        nameEstudiantesColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        apellidoEstudiantesColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        matriculaEstudiantesColumn.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());

        TableEstudiantes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                if (newValue != null) {
                    nameText.setText(newValue.getName());
                    lastNameText.setText(newValue.getFirstName());
                    matriculaText.setText(newValue.getMatricula());
                }
            }
        });
    }

    private void cerrarVentana() {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        stage.close();
    }
}
