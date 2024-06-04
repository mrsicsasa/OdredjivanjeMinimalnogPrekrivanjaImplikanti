package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Komponenta6Model;
import pogled.Komponenta6Pogled;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Komponenta6Model  model = new Komponenta6Model();
			Komponenta6Pogled pogled = new Komponenta6Pogled(model);
			Scene scene = new Scene(pogled.getRoot(), 1150, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("K6");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
