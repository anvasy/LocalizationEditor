package com.anvasy.ui.controller;

import com.anvasy.ui.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WelcomeController {

    private Stage primaryStage;

    @FXML
    @Getter
    private StackPane contentPane;

    @FXML
    private HBox titleBar;

    @FXML
    public void initialize() {
        double[] coords = new double[2];
        titleBar.setOnMousePressed(event -> {
            coords[0] = event.getSceneX();
            coords[1] = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - coords[0]);
            primaryStage.setY(event.getScreenY() - coords[1]);
        });
    }

    @FXML
    public void closeWindow() {
        primaryStage.close();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
