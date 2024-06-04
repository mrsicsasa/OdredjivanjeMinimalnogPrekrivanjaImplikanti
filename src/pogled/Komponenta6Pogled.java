package pogled;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Komponenta6Kontroler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Context;
import model.Komponenta6Model;

public class Komponenta6Pogled implements Initializable{

    private MenuBar menuBar;
    public BorderPane getRoot() {
		return root;
	}


	public void setRoot(BorderPane root) {
		this.root = root;
	}


	private BorderPane root;
    private Context context;
    private VBox content;
	
	public Komponenta6Pogled(Komponenta6Model model) {
        context = new Context();
        Komponenta6Kontroler kontroler = new Komponenta6Kontroler(model);
        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        Menu rezimRadaMenu = new Menu("Rezim rada");
        MenuItem projektantski = new MenuItem("Projektantski");
        MenuItem edukativni = new MenuItem("Edukativni");
        rezimRadaMenu.getItems().addAll(edukativni, projektantski);
        fileMenu.getItems().add(saveMenuItem);
        menuBar.getMenus().addAll(fileMenu, rezimRadaMenu);

        // Arrange buttons in layout
        content = new VBox();
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        // Arrange all parts in the main layout
        root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(content);

        // Set initial mode
        context.setMode(new ProjectMode());
        context.updateUI(content, kontroler);

        projektantski.setOnAction(e -> {
            context.setMode(new ProjectMode());
            context.updateUI(content, kontroler);
        });

//        edukativni.setOnAction(e -> {
//            model.setRezimRada("Edukativni");
//            context.setMode(new EducationalMode());
//            context.updateUI(content, model);
//        });

	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
