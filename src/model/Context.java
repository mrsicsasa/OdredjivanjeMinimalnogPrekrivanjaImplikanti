package model;

import controller.Komponenta6Kontroler;
import interfaces.Mode;
import javafx.scene.layout.Pane;

public class Context {
    private Mode currentMode;

    public void setMode(Mode mode) {
        this.currentMode = mode;
    }

    public void updateUI(Pane root, Komponenta6Kontroler kontroler) {
        currentMode.updateUI(root, kontroler);
    }
}
