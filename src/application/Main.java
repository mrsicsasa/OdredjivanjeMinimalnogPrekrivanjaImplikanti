package application;
	

import java.util.ArrayList;
import java.util.List;

import controller.QuineMcCluskeyController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.PrimeImplicant;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<PrimeImplicant> primeImplicants = new ArrayList<>();
		primeImplicants.add(new PrimeImplicant("zw'", List.of(2, 6, 10, 14)));
		primeImplicants.add(new PrimeImplicant("xy'", List.of(8, 9, 10, 11)));
		primeImplicants.add(new PrimeImplicant("xz", List.of(10, 11, 14, 15)));


	    List<Integer> minterms = List.of(2,6,8,9,10,11,14,15);
	    System.out.println("Initial Boolean Equation: xyz`w`"
	    		+ " + xyz`w");
	    //System.out.println(generateEquation(primeImplicants));
	    // Create controller and set prime implicants and minterms
	    QuineMcCluskeyController controller = new QuineMcCluskeyController();
	    controller.setPrimeImplicants(primeImplicants);
	    controller.setMinterms(minterms);

	    // Find essential implicants
	    List<String> essentialImplicants = controller.findEssentialImplicants();

	    // Combine essential implicants to minimize equation
	    String minimizedEquation = controller.combineImplicants(essentialImplicants);

	    // Print minimized equation
	    System.out.println("Minimized Boolean Equation: " + minimizedEquation);
		launch(args);
	}
	private static String generateEquation(List<PrimeImplicant> primeImplicants) {
	    StringBuilder equation = new StringBuilder();
	    for (PrimeImplicant implicant : primeImplicants) {
	        equation.append(implicant.getVariables()).append(" + ");
	    }
	    return equation.substring(0, equation.length() - 3); // Remove the last " + "
	}
}
