package com.anvasy.ui.controller;

import com.anvasy.service.ApplicationSettingsService;
import com.anvasy.ui.ViewManager;
import javafx.stage.Stage;

public interface ViewAware {
    void setViewManager(ViewManager viewManager);
    void setStage(Stage primaryStage);
    void setAppSettingsService(ApplicationSettingsService appSettingsService);
}
