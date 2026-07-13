package com.anvasy.ui.controller;

import com.anvasy.model.Locale;
import com.anvasy.model.Project;
import com.anvasy.service.ApplicationSettingsService;
import com.anvasy.service.ProjectLoader;
import com.anvasy.ui.ViewManager;
import com.anvasy.utils.ProjectUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateProjectController implements ViewAware, Initializable {

    private ViewManager viewManager;
    private Stage primaryStage;

    @FXML
    private TextField projectName;
    @FXML
    private Label directory;
    @FXML
    private ComboBox<Locale> localeComboBox;
    @FXML
    private Button createButton;

    private ApplicationSettingsService appSettingsService;

    @Override
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void setAppSettingsService(ApplicationSettingsService appSettingsService) {
        this.appSettingsService = appSettingsService;
    }

    public void showWelcome() {
        viewManager.showMainWelcome();
    }

    public void browseFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);

        directory.setText(selectedDirectory.getAbsolutePath());
    }

    public void createProject() throws Exception {
        Project project = ProjectLoader.createProject(projectName.getText(), directory.getText(), localeComboBox.getValue());
        appSettingsService.addRecentProject(project.getDirectory());
        viewManager.showEditor(project);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Locale> options =
                FXCollections.observableArrayList(
                        ProjectUtils.getAvailableLocales()
                );
        localeComboBox.setItems(options);
        localeComboBox.getSelectionModel().selectFirst();

        createButton.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> projectName.getText().trim().isEmpty() || directory.getText().isEmpty(),
                        projectName.textProperty(),
                        directory.textProperty()
                )
        );
    }

}
