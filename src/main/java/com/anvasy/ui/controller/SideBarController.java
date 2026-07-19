package com.anvasy.ui.controller;

import com.anvasy.model.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SideBarController implements Initializable {

    @FXML
    @Getter
    private VBox sidebarRoot;
    @FXML
    private ListView<String> localesListView;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}