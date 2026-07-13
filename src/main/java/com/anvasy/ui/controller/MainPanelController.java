package com.anvasy.ui.controller;

import com.anvasy.model.Project;
import com.anvasy.service.ApplicationSettingsService;
import com.anvasy.service.ProjectLoader;
import com.anvasy.ui.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MainPanelController implements ViewAware {

    private ViewManager viewManager;
    private Stage primaryStage;

    @FXML
    public ListView<String> recentProjects;

    private ApplicationSettingsService appSettingsService;

    @Override
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //TODO move list population from setter
    @Override
    public void setAppSettingsService(ApplicationSettingsService appSettingsService) {
        this.appSettingsService = appSettingsService;
        ObservableList<String> list = FXCollections.observableArrayList(appSettingsService.getRecentProjects());
        recentProjects.setItems(list);
    }

    public void showCreateProject() {
        viewManager.showCreateProject();
    }

    public void openProject() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your project-settings.json file");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(filter);

        File projectFile = fileChooser.showOpenDialog(primaryStage);

        Project project = ProjectLoader.openProject(projectFile);
        appSettingsService.addRecentProject(project.getDirectory());

        viewManager.showEditor(project);
    }

    //TODO add removed project check
    public void openRecentProject(MouseEvent mouseEvent) throws Exception {
        Project project = ProjectLoader.openProject(recentProjects.getSelectionModel().getSelectedItem());
        appSettingsService.addRecentProject(project.getDirectory());

        viewManager.showEditor(project);
    }
}
