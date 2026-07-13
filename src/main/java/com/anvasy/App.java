package com.anvasy;

import com.anvasy.service.ApplicationSettingsService;
import com.anvasy.ui.ViewManager;
import com.anvasy.ui.context.ApplicationContext;
import com.anvasy.ui.context.View;
import com.anvasy.ui.controller.WelcomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.WELCOME.getFxmlPath()));

        Parent root = loader.load();

        ApplicationContext applicationContext = new ApplicationContext(new ApplicationSettingsService());

        WelcomeController controller = loader.getController();
        ViewManager viewManager = new ViewManager(stage, controller.getContentPane(),
                applicationContext.getSettingsService());
        controller.setStage(stage);

        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        viewManager.showMainWelcome();
    }
}
