package com.anvasy.ui.controller;

public class CreateProjectController implements ControllerNavigator {

    private WelcomeController welcomeController;

    @Override
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }

    public void showWelcome() {
        welcomeController.showWelcome();
    }

    public void browseFolder() {

    }

    public void createProject() {

    }
}
