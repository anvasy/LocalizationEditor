package com.anvasy.ui.config;

import com.anvasy.model.Project;
import com.anvasy.ui.model.View;
import com.anvasy.ui.controller.EditorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViewManager {

    @Getter
    @Setter
    private Stage welcomeStage;
    @Getter
    private Stage editorStage;
    private final FxmlLoader fxmlLoader;

    @Setter
    private Pane contentPane;

    public ViewManager(FxmlLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void showWelcome() {
        switchScene(View.WELCOME);
    }

    public void showMainWelcome() {
        loadSubView(View.MAIN_WELCOME, this.contentPane);
    }

    public void showCreateProject() {
        loadSubView(View.CREATE_PROJECT, this.contentPane);
    }

    private void switchScene(View view) {
        Parent rootNode = loadRootNode(view.getFxmlPath());

        Scene scene = new Scene(rootNode);

        welcomeStage.setScene(scene);
        welcomeStage.initStyle(StageStyle.UNDECORATED);
        welcomeStage.show();
    }

    public void showEditorTable(HBox editorBox) {
        loadSubView(View.EDITOR_TABLE, editorBox);
    }

    public void showEmptyEditor(HBox editorBox) {
        loadSubView(View.EDITOR_EMPTY, editorBox);
    }

    private Parent loadRootNode(String fxmlPath) {
        Parent rootNode;
        try {
            rootNode = fxmlLoader.load(fxmlPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rootNode;
    }

    private void loadSubView(View view, Pane contentPane) {
        try {
            Parent parent = fxmlLoader.load(view.getFxmlPath());
            contentPane.getChildren().setAll(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showEditor(Project project) throws IOException {
        editorStage = new Stage();

        FXMLLoader loader = fxmlLoader.getLoader(View.EDITOR.getFxmlPath());
        loader.load();

        EditorController controller = loader.getController();
        controller.setProject(project);

        editorStage.setScene(new Scene(loader.getRoot()));
        editorStage.setTitle(project.getProjectName());
        editorStage.initStyle(StageStyle.UNDECORATED);
        editorStage.show();

        welcomeStage.close();
    }
}