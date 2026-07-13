package com.anvasy.ui.context;

import com.anvasy.service.ApplicationSettingsService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationContext {
    private final ApplicationSettingsService settingsService;
}
