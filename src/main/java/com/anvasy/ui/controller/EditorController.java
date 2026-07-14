package com.anvasy.ui.controller;

import com.anvasy.model.Project;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class EditorController {
    @Setter
    private Project project;
}
