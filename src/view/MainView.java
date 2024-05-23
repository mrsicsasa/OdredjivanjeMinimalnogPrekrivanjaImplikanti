package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.AppModel;

public class MainView {
    private MenuBar menuBar;
    private Label entryLabel;
    private Label exitLabel;
    private Label rezimRadaLabel;
    private Button pocniButton;
    private BorderPane root;

    public MainView(AppModel model) {
        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        Menu rezimRadaMenu = new Menu("Rezim rada");
        MenuItem projektantski = new MenuItem("Projektantski");
        MenuItem edukativni = new MenuItem("Edukativni");
        rezimRadaMenu.getItems().addAll(edukativni, projektantski);
        fileMenu.getItems().add(saveMenuItem);
        menuBar.getMenus().addAll(fileMenu, rezimRadaMenu);

        // Create labels
        entryLabel = new Label();
        entryLabel.textProperty().bind(Bindings.concat("Entry Function: ", model.entryFunctionProperty()));
        exitLabel = new Label();
        exitLabel.textProperty().bind(Bindings.concat("Exit Function: ", model.exitFunctionProperty()));
        rezimRadaLabel = new Label();
        rezimRadaLabel.textProperty().bind(Bindings.concat("Rezim rada: ", model.rezimRadaProperty()));

        // Create buttons
        pocniButton = new Button("Pocni");

        // Arrange labels and buttons in layout
        HBox displays = new HBox(entryLabel, exitLabel);
        displays.setSpacing(100);
        displays.setAlignment(Pos.CENTER);
        displays.setStyle("-fx-padding: 10;");

        HBox bottomRight = new HBox(rezimRadaLabel);
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRight.setStyle("-fx-padding: 10;");

        VBox content = new VBox(displays, pocniButton);
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        // Arrange all parts in the main layout
        root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(content);
        root.setBottom(bottomRight);
        
        projektantski.setOnAction(e -> model.setRezimRada("Projektantski"));
        edukativni.setOnAction(e -> model.setRezimRada("Edukativni"));
    }

    public BorderPane getRoot() {
        return root;
    }

    public Button getPocniButton() {
        return pocniButton;
    }
}
