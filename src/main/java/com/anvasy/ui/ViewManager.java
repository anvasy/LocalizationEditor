package com.anvasy.ui;

import com.anvasy.model.Project;
import com.anvasy.service.ApplicationSettingsService;
import com.anvasy.ui.context.View;
import com.anvasy.ui.controller.MainPanelController;
import com.anvasy.ui.controller.ViewAware;
import com.anvasy.ui.controller.EditorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class ViewManager {

    private Stage welcomeStage;
    private final StackPane contentPane;

    private ApplicationSettingsService appSettingsService;

    public void showWelcome() {
        loadWelcomeSubView(View.WELCOME, contentPane);
    }

    public void showMainWelcome() {
        loadWelcomeSubView(View.MAIN_WELCOME, contentPane);
    }

    public void showCreateProject() {
        loadWelcomeSubView(View.CREATE_PROJECT, contentPane);
    }

    public void showEditor(Project project) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.EDITOR.getFxmlPath()));
        Parent root = loader.load();

        EditorController controller = loader.getController();
        controller.setProject(project);

        Stage editorStage = new Stage();

        editorStage.setScene(new Scene(root));
        editorStage.setTitle(project.getProjectName());
        editorStage.show();

        welcomeStage.close();
    }

    private void loadWelcomeSubView(View view, Pane contentPane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view.getFxmlPath()));
            Parent parent = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ViewAware) {
                ((ViewAware) controller).setViewManager(this);
                ((ViewAware) controller).setStage(welcomeStage);
                ((ViewAware) controller).setAppSettingsService(appSettingsService);
            }

            contentPane.getChildren().setAll(parent);
        } catch (IOException e) {
            log.error("An exception during view loading: ", e);
        }
    }

}
