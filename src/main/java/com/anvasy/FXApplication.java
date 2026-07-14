package com.anvasy;

import com.anvasy.ui.config.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class FXApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private ViewManager viewManager;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(Main.class).run();
    }

    @Override
    public void start(Stage primaryStage) {
        viewManager = applicationContext.getBean(ViewManager.class, primaryStage);
        viewManager.showWelcome();
    }

    @Override
    public void stop() {
        applicationContext.close();
    }
}
