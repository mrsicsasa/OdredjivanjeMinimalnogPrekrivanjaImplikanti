package view;

import controller.MainController;
import javafx.scene.layout.Pane;

public interface Mode {
    void updateUI(Pane root, MainController controler);
}
