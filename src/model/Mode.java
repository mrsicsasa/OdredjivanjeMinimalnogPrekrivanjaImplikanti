package model;

import javafx.scene.layout.Pane;

public interface Mode {
    void updateUI(Pane root, AppModel model);
}
