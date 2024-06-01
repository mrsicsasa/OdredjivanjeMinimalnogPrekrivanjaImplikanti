package view;

import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class MainView {
    private MenuBar menuBar;
    private BorderPane root;
    private Context context;
    private VBox content;

    public MainView(MainController controler) {
        context = new Context();

        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        Menu rezimRadaMenu = new Menu("Rezim rada");
        MenuItem projektantski = new MenuItem("Projektantski");
        MenuItem edukativni = new MenuItem("Edukativni");
        rezimRadaMenu.getItems().addAll(edukativni, projektantski);
        fileMenu.getItems().add(saveMenuItem);
        menuBar.getMenus().addAll(fileMenu, rezimRadaMenu);

        // Arrange buttons in layout
        content = new VBox();
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        // Arrange all parts in the main layout
        root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(content);

        // Set initial mode
        context.setMode(new ProjectMode());
        context.updateUI(content, controler);

        projektantski.setOnAction(e -> {
        	controler.getModel().setRezimRada("Projektantski");
            context.setMode(new ProjectMode());
            context.updateUI(content, controler);
        });

        edukativni.setOnAction(e -> {
        	controler.getModel().setRezimRada("Edukativni");
            context.setMode(new EducationalMode());
            context.updateUI(content, controler);
        });
    }

    public BorderPane getRoot() {
        return root;
    }
}
