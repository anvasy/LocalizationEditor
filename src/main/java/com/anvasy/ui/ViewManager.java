package com.anvasy.ui;

import com.anvasy.model.Project;
import com.anvasy.ui.context.View;
import com.anvasy.ui.controller.ControllerNavigator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ViewManager {

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showWelcome(Pane pane) {
        loadSubView(View.WELCOME, pane);
    }

    public void showCreateProject(Pane pane) {
        loadSubView(View.CREATE_PROJECT, pane);
    }

    public void showEditor(Project project) {
        load(View.EDITOR);
    }

    private void load(View view) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource(view.getFxmlPath()));
            Parent root = loader.load();
            //contentPane.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSubView(View view, Pane contentPane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view.getFxmlPath()));
            Parent parent = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ControllerNavigator) {
                ((ControllerNavigator) controller).setStage(stage);
            }

            contentPane.getChildren().setAll(parent);
        } catch (IOException e) {
            log.error("An exception during view loading: ", e);
        }
    }

}
