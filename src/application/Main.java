package application;
	

import java.util.ArrayList;
import java.util.List;

import controller.QuineMcCluskeyController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Implicant;
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
		ArrayList<Implicant> implicants = new ArrayList<>();
		implicants.add(new Implicant("zw'", List.of(2, 6, 10, 14)));
		implicants.add(new Implicant("xy'", List.of(8, 9, 10, 11)));
		implicants.add(new Implicant("xz", List.of(10, 11, 14, 15)));

		Implicant initialImplicant=new Implicant("xyz`w`"+ " + xyz`w", List.of(2,6,8,9,10,11,14,15));
	    
	    System.out.println("Initial Boolean Equation:"+initialImplicant.getVariables());
	    //System.out.println(generateEquation(primeImplicants));
	    // Create controller and set prime implicants and minterms
	    QuineMcCluskeyController controller = new QuineMcCluskeyController();
	    controller.setPrimeImplicants(implicants);
	    controller.setMinterms(initialImplicant.getImplicants());

	    // Find essential implicants
	    List<String> essentialImplicants = controller.findEssentialImplicants();

	    // Combine essential implicants to minimize equation
	    String minimizedEquation = controller.combineImplicants(essentialImplicants);

	    // Print minimized equation
	    System.out.println("Minimized Boolean Equation: " + minimizedEquation);
		launch(args);
	}
	private static String generateEquation(List<Implicant> implicants) {
	    StringBuilder equation = new StringBuilder();
	    for (Implicant implicant : implicants) {
	        equation.append(implicant.getVariables()).append(" + ");
	    }
	    return equation.substring(0, equation.length() - 3); // Remove the last " + "
	}
}
