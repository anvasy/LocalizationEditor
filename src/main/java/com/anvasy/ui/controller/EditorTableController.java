package com.anvasy.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditorTableController {
    private static final int MAX_COLUMNS = 4;

    @FXML
    private HBox columnsContainer;
    @FXML
    private Button btnAddColumn;

    @FXML
    public void initialize() {
        addColumn("English (United States)");
    }

    @FXML
    private void onAddColumnPressed() {
        if (columnsContainer.getChildren().size() < MAX_COLUMNS) {
            addColumn("Italian");
        }
        if (columnsContainer.getChildren().size() >= MAX_COLUMNS) {
            btnAddColumn.setDisable(true);
        }
    }

    private void addColumn(String localeName) {
        VBox column = new VBox();
        column.setPrefWidth(280.0);

        HBox header = new HBox();
        Label title = new Label(localeName);
        Button closeBtn = new Button("✕");
        closeBtn.setOnAction(e -> removeColumn(column));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(title, spacer, closeBtn);

        VBox rowsContainer = new VBox(14);

        List<String> keys = List.of("main.menu", "main.pause", "dialogue.hero_victory");

        for (String key : keys) {
            VBox cell = new VBox(5);

            Label keyLabel = new Label(key);

            TextArea textInput = new TextArea();
            textInput.setPrefHeight(65);
            textInput.setWrapText(true);

            cell.getChildren().addAll(keyLabel, textInput);
            rowsContainer.getChildren().add(cell);
        }

        column.getChildren().addAll(header, rowsContainer);
        columnsContainer.getChildren().add(column);
    }

    private void removeColumn(VBox column) {
        columnsContainer.getChildren().remove(column);
        if (columnsContainer.getChildren().size() < MAX_COLUMNS) {
            btnAddColumn.setDisable(false);
        }
    }
}