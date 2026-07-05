package com.anvasy.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class WelcomeController {

    private Stage primaryStage;

    @FXML
    private StackPane contentPane;
    @FXML
    private HBox titleBar;

    @FXML
    public void initialize(Stage stage) {
        this.primaryStage = stage;
        showWelcome();

        double xOffset = 0;
        double yOffset = 0;
        titleBar.setOnMousePressed(event -> {
            //xOffset = event.getSceneX();
            //yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    public void showWelcome() {
        loadSubView("/fxml/pane/MainPane.fxml");
    }

    @FXML
    public void showCreateProject() {
        loadSubView("/fxml/pane/CreateProjectPane.fxml");
    }

    public void loadSubView(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent view = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ControllerNavigator) {
                ((ControllerNavigator) controller).setWelcomeController(this);
                ((ControllerNavigator) controller).setStage(primaryStage);
            }

            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            log.error("An exception during view loading: ", e);
        }
    }

    @FXML
    public void closeWindow() {
        primaryStage.close();
    }

}
