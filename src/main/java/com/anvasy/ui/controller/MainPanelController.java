package com.anvasy.ui.controller;

import com.anvasy.model.Project;
import com.anvasy.service.ApplicationSettingsService;
import com.anvasy.service.ProjectLoader;
import com.anvasy.ui.config.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainPanelController implements Initializable {

    @FXML
    public ListView<String> recentProjects;

    private final ViewManager viewManager;
    private final ApplicationSettingsService appSettingsService;

    @Lazy
    public MainPanelController(ViewManager viewManager,
                               ApplicationSettingsService appSettingsService) {
        this.viewManager = viewManager;
        this.appSettingsService = appSettingsService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        File projectFile = fileChooser.showOpenDialog(viewManager.getWelcomeStage());

        Project project = ProjectLoader.openProject(projectFile);
        appSettingsService.addRecentProject(project.getDirectory());

        viewManager.showEditor(project);
    }

    //TODO add removed project check
    public void openRecentProject() throws Exception {
        Project project = ProjectLoader.openProject(recentProjects.getSelectionModel().getSelectedItem());
        appSettingsService.addRecentProject(project.getDirectory());

        viewManager.showEditor(project);
    }

}
