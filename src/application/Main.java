package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import viewT.MainGUI;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.start(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
