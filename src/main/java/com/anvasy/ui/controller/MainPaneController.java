package com.anvasy.ui.controller;

import com.anvasy.model.Project;
import com.anvasy.service.ProjectLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainPaneController implements ControllerNavigator {

    private WelcomeController welcomeController;
    private Stage primaryStage;

    @FXML
    public ListView recentProjects;

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

    public void openProject() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your project-settings.json file");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(filter);

        File projectFile = fileChooser.showOpenDialog(primaryStage);

        Project project = ProjectLoader.openProject(projectFile);
        primaryStage.close();
    }
}
