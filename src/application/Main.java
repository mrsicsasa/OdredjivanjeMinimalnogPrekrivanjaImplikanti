package application;

import controller.DataController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AppModel;
import model.DobavljeniPodaci;
import view.MainView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Initialize the model, view, and controller
			AppModel model = new AppModel();
			MainView view = new MainView(model);
//			MainController controller = new MainController(model, view);
			DataController dc = new DataController();
			DobavljeniPodaci dp = dc.readFromCSVFile();
			System.out.println(dp.getImplikante());
			System.out.println(dp.getUlaznaFunkcija());
			// Set up the scene and stage
			Scene scene = new Scene(view.getRoot(), 1150, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("K6");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
