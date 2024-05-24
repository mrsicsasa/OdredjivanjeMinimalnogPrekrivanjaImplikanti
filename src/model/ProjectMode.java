package model;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.AppModel;

public class ProjectMode implements Mode {
    @Override
    public void updateUI(Pane root, AppModel model) {
        root.getChildren().clear();
        Label label = new Label("Projektantski režim aktivan");
        label.textProperty().bind(Bindings.concat("Rezim rada: ", model.rezimRadaProperty()));
        root.getChildren().add(label);

        Label entryLabel = new Label();
        entryLabel.textProperty().bind(Bindings.concat("Entry Function: ", model.entryFunctionProperty()));
        Label exitLabel = new Label();
        exitLabel.textProperty().bind(Bindings.concat("Exit Function: ", model.exitFunctionProperty()));

        HBox displays = new HBox(entryLabel, exitLabel);
        displays.setSpacing(100);
        displays.setAlignment(Pos.CENTER);
        displays.setStyle("-fx-padding: 10;");

        Button pocniButton = new Button("Pocni");
        pocniButton.setOnAction(e -> model.updateExitFunction());

        VBox content = new VBox(displays, pocniButton);
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        root.getChildren().add(content);
    }

}

