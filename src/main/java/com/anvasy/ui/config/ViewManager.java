package com.anvasy.ui.config;

import com.anvasy.model.Project;
import com.anvasy.ui.context.View;
import com.anvasy.ui.controller.EditorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private final Stage primaryStage;
    private final FxmlLoader fxmlLoader;

    @Setter
    private Pane contentPane;

    public ViewManager(FxmlLoader fxmlLoader,
                       Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.fxmlLoader = fxmlLoader;
    }

    public void showWelcome() {
        switchScene(View.WELCOME);
    }

    public void showMainWelcome() {
        loadWelcomeSubView(View.MAIN_WELCOME, this.contentPane);
    }

    public void showCreateProject() {
        loadWelcomeSubView(View.CREATE_PROJECT, this.contentPane);
    }

    private void switchScene(View view) {
        Parent rootNode = loadRootNode(view.getFxmlPath());

        Scene scene = new Scene(rootNode);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
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

    private void loadWelcomeSubView(View view, Pane contentPane) {
        try {
            Parent parent = fxmlLoader.load(view.getFxmlPath());

            contentPane.getChildren().setAll(parent);
        } catch (IOException e) {
            //log.error("An exception during view loading: ", e);
        }
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

        primaryStage.close();
    }
}