package com.anvasy.ui.controller;

import com.anvasy.service.ProjectLoader;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class OpenProjectController {

    private ProjectLoader projectLoader = new ProjectLoader();
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void onCreateButtonClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        //TODO: set current dir as root; later move to settings to memorise

        File file = directoryChooser.showDialog(primaryStage);
        if (file != null) {
            projectLoader.createProject(file.toPath());
        }
    }

    @FXML
    protected void onOpenProject() {

    }

}
