package model;

import javafx.scene.layout.Pane;

public class Context {
    private Mode currentMode;

    public void setMode(Mode mode) {
        this.currentMode = mode;
    }

    public void updateUI(Pane root, AppModel model) {
        currentMode.updateUI(root, model);
    }
}
