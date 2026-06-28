package com.anvasy.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OpenProjectController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello, World!");
    }
}
