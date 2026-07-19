package com.anvasy.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum View {
    WELCOME("/fxml/view/WelcomeView.fxml"),
    MAIN_WELCOME("/fxml/panels/MainPanel.fxml"),
    CREATE_PROJECT("/fxml/panels/CreateProjectPanel.fxml"),
    EDITOR("/fxml/view/EditorView.fxml"),
    SIDEBAR("/fxml/panels/SideBar.fxml"),
    EDITOR_TABLE("/fxml/panels/EditorTable.fxml"),
    EDITOR_EMPTY("/fxml/panels/EditorViewEmpty.fxml");

    private final String fxmlPath;
}
