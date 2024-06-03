package view;

import java.util.List;
import java.util.Set;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.AppModel;
import model.Implicant;
import model.Mode;

public class EducationalModeEssentialView implements Mode {
    private TableView<Implicant> tableView;
    private Label explanationLabel;
    private VBox rightPane;
    private Set<Implicant> essentialImplicants;
	private Set<Implicant> incorrectImplicants;
	private Implicant selectedImplicant;
    public TableView<Implicant> getTableView() {
        return tableView;
    }

    public VBox getRightPane() {
        return rightPane;
    }

    @Override
    public void updateUI(Pane root, AppModel model) {
       	 root.getChildren().clear();
	        tableView = new TableView<>();
	        explanationLabel = new Label("Naucicete kako da minimizirate prekidacku funkciju koriscenjem McCluskey metode.");
            rightPane = new VBox(10, new Label("Objasnjenje:"), explanationLabel);
            rightPane.setPadding(new Insets(10));
	        BorderPane content = new BorderPane();
	        content.setCenter(tableView);
	        content.setRight(rightPane);
	        
	        root.getChildren().add(content);
        }
	private void addRowClickListener() {
        tableView.setRowFactory(tv -> {
            TableRow<Implicant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                System.out.println("Clicked on row!");
                if (!row.isEmpty()) {
                    Implicant clickedItem = row.getItem();
                    handleRowClick(clickedItem);
                }
            });
            return row;
        });
    }

	private void handleRowClick(Implicant clickedItem) {
		System.out.println("Row clicked: " + clickedItem);
		if (essentialImplicants.contains(clickedItem)) {
			essentialImplicants.remove(clickedItem);
		} else {
			essentialImplicants.add(clickedItem);
		}

		// Ponovno iscrtavanje tabele nakon ažuriranja podataka
		tableView.refresh();
	}
        
    }


