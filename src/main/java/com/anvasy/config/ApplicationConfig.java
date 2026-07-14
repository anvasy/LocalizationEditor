package com.anvasy.config;

import com.anvasy.ui.config.FxmlLoader;
import com.anvasy.ui.config.ViewManager;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ApplicationConfig {

    private final FxmlLoader fxmlLoader;

    public ApplicationConfig(FxmlLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    @Bean
    @Lazy
    public ViewManager viewManager(Stage stage) {
        return new ViewManager(fxmlLoader, stage);
    }
}