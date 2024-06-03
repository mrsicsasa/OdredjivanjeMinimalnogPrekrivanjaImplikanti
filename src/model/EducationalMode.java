package model;

import javafx.scene.layout.Pane;
import controller.EducationalModeController;
import view.EducationalModeView;

public class EducationalMode implements Mode {
    private EducationalModeController controller;
    private EducationalModeView view;

    public EducationalMode(AppModel model) {
        this.view = new EducationalModeView();
        this.controller = new EducationalModeController(model, view);
    }

    @Override
    public void updateUI(Pane root, AppModel model) {
        controller.updateUI(root);
    }
}
