package view;

import controller.MainController;
import javafx.scene.layout.Pane;

public class Context {
    private Mode currentMode;

    public void setMode(Mode mode) {
        this.currentMode = mode;
    }

    public void updateUI(Pane root, MainController controler) {
        currentMode.updateUI(root, controler);
    }
}
