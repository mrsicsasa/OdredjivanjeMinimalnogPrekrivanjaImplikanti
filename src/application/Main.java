package application;

import controller.Komponenta6Kontroler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Komponenta6Model;
import view.Komponenta6Pogled;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            TabPane tabPane = new TabPane();

            Komponenta6Model model = new Komponenta6Model();
            Komponenta6Pogled view = new Komponenta6Pogled();
            Komponenta6Kontroler controller = new Komponenta6Kontroler(model, view);
            controller.initialize();


            Tab tab = new Tab("Komponenta6");
            tab.setContent(view.getLayout());

            tabPane.getTabs().add(tab);
            Scene scene = new Scene(tabPane, 700, 500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Projektovanje mreze sa memorijom (LPMM)");
            primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
