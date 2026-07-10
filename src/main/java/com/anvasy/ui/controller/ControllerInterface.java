package com.anvasy.ui.controller;

import com.anvasy.ui.ViewManager;
import javafx.stage.Stage;

public interface ControllerInterface {
    void setViewManager(ViewManager viewManager);
    void setStage(Stage primaryStage);
}
