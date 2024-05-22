package viewT;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Kreiranje meni-a
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        Menu rezimRadaMenu = new Menu("Rezim rada");
        MenuItem projektantski = new MenuItem("Projektantski");
        MenuItem edukativni = new MenuItem("Edukativni");
        rezimRadaMenu.getItems().addAll(edukativni, projektantski);
        fileMenu.getItems().add(saveMenuItem);
        menuBar.getMenus().addAll(fileMenu, rezimRadaMenu);

        // Test primer, kasnije cemo dodati da se vrednosti dobijaju kroz api/funkcije
        Label entryLabel = new Label("Entry Function: " + " f(A,B,C,D) = Σm(0,2,5,7,8,10,12,13,15) ");
        Label exitLabel = new Label("Exit Function: " + " f(A, B, C, D) = A'CD' + AB'D + BC'D' + ACD + BCD'");
        Label rezimRadaLabel = new Label("Rezim rada: Projektantski");
        // Kreiranje dugmeta "Pocni"
        Button pocniButton = new Button("Pocni");

        // Centriranje labela 
        HBox displays = new HBox(entryLabel, exitLabel);
        displays.setSpacing(100);
        displays.setAlignment(Pos.CENTER);
        displays.setStyle("-fx-padding: 10;");
        
        HBox bottomRight = new HBox(rezimRadaLabel);
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRight.setStyle("-fx-padding: 10;");
        // Kreiranje VBox-a za label-e i dugme
        VBox content = new VBox(displays, pocniButton);
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        // Centriranje delova koji ce biti na sceni
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(content);
        root.setBottom(bottomRight);

        // Kreiranje scene
        Scene scene = new Scene(root, 600, 400);

        // Set Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("K6");
        primaryStage.show();
    }

}
