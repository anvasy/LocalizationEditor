package com.anvasy.ui.controller;

import com.anvasy.model.Project;
import com.anvasy.service.ProjectLoader;
import com.anvasy.ui.config.ViewManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditorController implements Initializable {

    @FXML
    public HBox editorTable;
    @FXML
    private SplitPane mainSplitPane;
    @FXML
    private Button btnToggleSidebar;
    @FXML
    private HBox titleBar;

    @Setter
    private Project project;

    private final ViewManager viewManager;

    private boolean isCollapsed = false;
    private double lastDividerPosition = 0.22;

    public EditorController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage primaryStage = viewManager.getEditorStage();

        double[] coords = new double[2];
        titleBar.setOnMousePressed(event -> {
            coords[0] = event.getSceneX();
            coords[1] = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - coords[0]);
            primaryStage.setY(event.getScreenY() - coords[1]);
        });

        if (ProjectLoader.getLocaleFiles().isEmpty()) {
            viewManager.showEmptyEditor(editorTable);
        } else {
            viewManager.showEditorTable(editorTable);
        }
    }

    @FXML
    private void onToggleSidebarPressed() {
        SplitPane.Divider divider = mainSplitPane.getDividers().getFirst();
        Timeline timeline = new Timeline();
        double targetPosition;

        if (isCollapsed) {
            targetPosition = lastDividerPosition;
        } else {
            lastDividerPosition = divider.getPosition();
            targetPosition = 0.0;
        }

        KeyValue kv = new KeyValue(divider.positionProperty(), targetPosition);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);

        timeline.getKeyFrames().add(kf);

        isCollapsed = !isCollapsed;

        timeline.play();
    }

    public void closeWindow() {
        viewManager.getEditorStage().close();
    }

    public void minimizeWindow() {
        viewManager.getEditorStage().setIconified(true);
    }
}