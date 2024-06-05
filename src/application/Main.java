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
			  Implikant implikant1 = new Implikant("F1", List.of(1, 2, 3));
		        Implikant implikant2 = new Implikant("F2", List.of(2, 4, 6));
		        Implikant implikant3 = new Implikant("F3", List.of(1, 3, 5));
		        model.getImplikanti().add(implikant1);
		        model.getImplikanti().add(implikant2);
		        model.getImplikanti().add(implikant3);
		        Komponenta6View view = new Komponenta6View();
		        Komponenta6Controller controller = new Komponenta6Controller(model, view);
		        controller.initialize();
		        // Postavljanje početnih podataka
		        controller.setModelData("F1+F2+F3"); //pocetna funkcija
		        // Ažuriranje prikaza sa podrazumevanim stanjem (ProjektantskiState)
		        controller.updateView();
		        Scene scene = new Scene(view.getLayout(), 400, 300);
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
