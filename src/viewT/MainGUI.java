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
        fileMenu.getItems().add(saveMenuItem);
        menuBar.getMenus().add(fileMenu);

        // Test primer, kasnije cemo dodati da se vrednosti dobijaju kroz api/funkcije
        Label entryLabel = new Label("Entry Function: " + " f(A,B,C,D) = Σm(0,2,5,7,8,10,12,13,15) ");
        Label exitLabel = new Label("Exit Function: " + " f(A, B, C, D) = A'CD' + AB'D + BC'D' + ACD + BCD'");

        // Kreiranje dugmeta "Pocni"
        Button pocniButton = new Button("Pocni");

        // Centriranje labela 
        HBox displays = new HBox(entryLabel, exitLabel);
        displays.setSpacing(100);
        displays.setAlignment(Pos.CENTER);
        displays.setStyle("-fx-padding: 10;");

        // Kreiranje VBox-a za label-e i dugme
        VBox content = new VBox(displays, pocniButton);
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        // Centriranje delova koji ce biti na sceni
        VBox root = new VBox(menuBar, content);
        root.setAlignment(Pos.TOP_CENTER);

        // Kreiranje scene
        Scene scene = new Scene(root, 600, 400);

        // Set Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("K6");
        primaryStage.show();
    }

}
