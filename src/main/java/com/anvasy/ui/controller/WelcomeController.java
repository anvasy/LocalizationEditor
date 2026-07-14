package com.anvasy.ui.controller;

import com.anvasy.ui.config.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WelcomeController implements Initializable {

    @Lazy
    @Autowired
    private ViewManager viewManager;

    @FXML
    private StackPane contentPane;

    @FXML
    private HBox titleBar;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage primaryStage = viewManager.getPrimaryStage();

        double[] coords = new double[2];
        titleBar.setOnMousePressed(event -> {
            coords[0] = event.getSceneX();
            coords[1] = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - coords[0]);
            primaryStage.setY(event.getScreenY() - coords[1]);
        });

        viewManager.setContentPane(contentPane); //TODO: fix?
        viewManager.showMainWelcome();
    }

    @FXML
    public void closeWindow() {
        viewManager.getPrimaryStage().close();
    }

}
