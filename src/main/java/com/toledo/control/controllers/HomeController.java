package com.toledo.control.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.toledo.control.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button guardarButton;

    @FXML
    private Button verButton;

    @FXML
    private Button actualizarButton;

    @FXML
    private ImageView regresarButton;

    @FXML
    void onMouseClickedActualizarrButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("actualizar-estudiante-view.fxml"));
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
    void onMouseClickedGuardarButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("guardar-estudiante-view.fxml"));
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
    void onMouseClickedRegresarButton(MouseEvent event) {
        cerrarVentana();
    }

    @FXML
    void onMouseClickedVerButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ver-estudiantes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/toledo/control/imagenes/DescartesLogo.png")));
        stage.show();
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    void initialize() {
    }
}
