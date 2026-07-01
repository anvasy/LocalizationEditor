package com.anvasy;

import com.anvasy.ui.controller.OpenProjectController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/fxml/OpenProjectView.fxml"));

        Parent root = loader.load();

        OpenProjectController controller = loader.getController();
        controller.setPrimaryStage(stage);

        Scene scene = new Scene(root);

        stage.setTitle("Localization Tool");
        stage.setScene(scene);
        stage.show();
    }
}
