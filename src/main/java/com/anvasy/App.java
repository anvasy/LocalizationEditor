package com.anvasy;

import com.anvasy.ui.controller.WelcomeController;
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
                new FXMLLoader(getClass().getResource("/fxml/view/WelcomeView.fxml"));

        Parent root = loader.load();

        WelcomeController controller = loader.getController();
        controller.initialize();

        Scene scene = new Scene(root);

        stage.setTitle("Localization Tool");
        stage.setScene(scene);
        stage.show();
    }
}
