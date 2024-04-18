package com.toledo.control.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.toledo.control.models.*;
import com.toledo.control.App;
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

public class VerEstudiantesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button verButton;

    @FXML
    private ImageView regresarButton;

    //MySQL
    @FXML
    private TableView<Student> TableMySQL;

    @FXML
    private TableColumn<Student, String> nameSQLColumn;

    @FXML
    private TableColumn<Student, String> apellidoSQLColumn;

    @FXML
    private TableColumn<Student, String> matriculaSQLColumn;

    //Acces
    @FXML
    private TableView<Student> TableAccess;

    @FXML
    private TableColumn<Student, String> nameAccessColumn;

    @FXML
    private TableColumn<Student, String> apellidoAccessColumn;

    @FXML
    private TableColumn<Student, String> matriculaAccessColumn;

    //SQLite
    @FXML
    private TableView<Student> TableSQLite;

    @FXML
    private TableColumn<Student, String> nameSQLiteColumn;

    @FXML
    private TableColumn<Student, String> apellidoSQLiteColumn;

    @FXML
    private TableColumn<Student, String> matriculaSQLiteColumn;


    @FXML
    private TextField matriculaBuscarText;

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
        MySQL mySQL = new MySQL();
        SQLite sqLite = new SQLite();
        Access access = new Access();

        if (control !=null && control.getMySQL()!=null && control.getMySQL().getEstudiantes()!=null) {
            TableMySQL.getItems().addAll(control.getMySQL().getEstudiantes());
            TableAccess.getItems().addAll(control.getAccess().getEstudiantes());
            TableSQLite.getItems().addAll(control.getSQLite().getEstudiantes());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("La lista se encuentra vacía.");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        //Access
        nameAccessColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        apellidoAccessColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        matriculaAccessColumn.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());

        //MySQL
        nameSQLColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        apellidoSQLColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        matriculaSQLColumn.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());

        //SQLite
        nameSQLiteColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        apellidoSQLiteColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        matriculaSQLiteColumn.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
    }

    private void cerrarVentana() {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        stage.close();
    }
}
