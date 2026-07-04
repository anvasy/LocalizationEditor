package com.anvasy.ui.controller;

import javafx.stage.Stage;

public class MainPaneController implements ControllerNavigator {

    private WelcomeController welcomeController;
    private Stage primaryStage;

    @Override
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }

    @Override
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showCreateProject() {
        welcomeController.showCreateProject();
    }

    public void openProject() {

    }
}
