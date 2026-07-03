package com.anvasy.ui.controller;

public class MainPaneController implements ControllerNavigator {

    private WelcomeController welcomeController;

    @Override
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }

    public void showCreateProject() {
        welcomeController.showCreateProject();
    }

    public void openProject() {

    }
}
