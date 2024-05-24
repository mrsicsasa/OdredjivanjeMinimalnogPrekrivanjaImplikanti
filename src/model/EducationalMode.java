package model;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
public class EducationalMode implements Mode {
    @Override
    public void updateUI(Pane root, AppModel model) {
        root.getChildren().clear();
        Label label = new Label("Edukativni režim aktivan");
        label.textProperty().bind(Bindings.concat("Rezim rada: ", model.rezimRadaProperty()));
        root.getChildren().add(label);
        // Add other UI components for educational mode if needed
    }
}
