package com.anvasy.ui.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum View {
    WELCOME("/fxml/view/WelcomeView.fxml"),
    MAIN_WELCOME("/fxml/panels/MainPanel.fxml"),
    CREATE_PROJECT("/fxml/panels/CreateProjectPanel.fxml"),
    EDITOR("/fxml/view/EditorView.fxml");

    private final String fxmlPath;
}
