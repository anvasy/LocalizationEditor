package com.anvasy.ui.controller;

import com.anvasy.ui.ViewManager;
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

    private ViewManager viewManager;
    private Stage primaryStage;

    @FXML
    private StackPane contentPane;
    @FXML
    private HBox titleBar;

    @FXML
    public void initialize(Stage stage) {
        this.primaryStage = stage;
        showWelcome();

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
    public void showWelcome() {
        loadSubView("/fxml/panels/MainPanel.fxml");
    }

    @FXML
    public void showCreateProject() {
        loadSubView("/fxml/panels/CreateProjectPanel.fxml");
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
