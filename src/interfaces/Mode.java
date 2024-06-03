package interfaces;

import javafx.scene.layout.Pane;
import model.AppModel;

public interface Mode {
    void updateUI(Pane root, AppModel model);
}
