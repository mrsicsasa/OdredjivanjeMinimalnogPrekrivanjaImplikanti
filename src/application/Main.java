package application;

import java.util.ArrayList;
import java.util.List;

import controller.Komponenta6Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Implikant;
import model.Komponenta6Model;
import view.Komponenta6View;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		  	Komponenta6Model model = new Komponenta6Model();
	        Komponenta6View view = new Komponenta6View();
	        Komponenta6Controller controller = new Komponenta6Controller(model, view);
	        controller.initialize();
	        // Postavljanje početnih podataka
	        controller.setModelData(model.getPocetnaFunkcija()); //pocetna funkcija
	        // Ažuriranje prikaza sa podrazumevanim stanjem (ProjektantskiState)
	        controller.updateView();
	        Scene scene = new Scene(view.getLayout(), 700, 500);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("MVC State Pattern Example");
	        primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
