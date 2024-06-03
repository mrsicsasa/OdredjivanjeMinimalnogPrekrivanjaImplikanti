package model;

import javafx.scene.layout.Pane;
import controller.EducationalModeEssentialController;
import view.EducationalModeEssentialView;

public class EducationalModeEssential implements Mode {
    private EducationalModeEssentialController controller;
    private EducationalModeEssentialView view;

    public EducationalModeEssential(AppModel model) {
        this.view = new EducationalModeEssentialView();
        this.controller = new EducationalModeEssentialController(model, view);
    }

    @Override
    public void updateUI(Pane root, AppModel model) {
        controller.updateUI(root);
    }

}
