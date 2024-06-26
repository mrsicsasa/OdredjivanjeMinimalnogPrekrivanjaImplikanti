package view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.Set;

public class Komponenta6Pogled {
    private ComboBox<String> comboBox;
    private TableView<List<String>> tableView;
    private VBox layout;
    private Label pocetnaFunkcijaLabel;
    private Label izlaznaFunkcijaLabel;
    private Label pocetnaFunkcijaValueLabel;
    private Label izlaznaFunkcijaValueLabel;

	public Komponenta6Pogled() {
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Projektantski", "Edukativni");
        comboBox.setValue("Projektantski");
        comboBox.getStyleClass().add("custom-button");

        tableView = new TableView<>();
        pocetnaFunkcijaLabel = new Label("Pocetna funkcija: ");
        pocetnaFunkcijaValueLabel = new Label(""); // Prazan label koji će se ažurirati iz kontrolera
        pocetnaFunkcijaLabel.getStyleClass().add("custom-label");
        pocetnaFunkcijaValueLabel.getStyleClass().add("custom-label");

        HBox pocetnaFunkcijaLayout = new HBox(pocetnaFunkcijaLabel, pocetnaFunkcijaValueLabel);
        pocetnaFunkcijaLayout.getStyleClass().add("hbox");
        
        izlaznaFunkcijaLabel = new Label("Izlazna funkcija: ");
        izlaznaFunkcijaValueLabel = new Label(""); // Prazan label koji će se ažurirati iz kontrolera
        izlaznaFunkcijaLabel.getStyleClass().add("custom-label");
        izlaznaFunkcijaValueLabel.getStyleClass().add("custom-label");

        HBox izlaznaFunkcijaLayout = new HBox(izlaznaFunkcijaLabel, izlaznaFunkcijaValueLabel);
        izlaznaFunkcijaLayout.getStyleClass().add("hbox");
        layout = new VBox(comboBox, pocetnaFunkcijaLayout, izlaznaFunkcijaLayout, tableView);
        layout.setAlignment(Pos.CENTER);
    }

    public void updateView(String data) {
        // Ova metoda može biti dodatno proširena ako želite ažurirati prikaz sa dodatnim podacima
    }

    public void updateImplikanti(ObservableList<List<String>> implikantiData) {
        tableView.setItems(implikantiData);
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public VBox getLayout() {
        return layout;
    }

    public void setTableColumns(Set<Integer> uniqueValues) {
        tableView.getColumns().clear();

        // Kreiranje kolone za funkciju
        TableColumn<List<String>, String> funkcijaColumn = new TableColumn<>("Funkcija");

        funkcijaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(0)));
        tableView.getColumns().add(funkcijaColumn);

        // Kreiranje dodatnih kolona za jedinstvene vrednosti
        for (Integer value : uniqueValues) {
            TableColumn<List<String>, String> valueColumn = new TableColumn<>(String.valueOf(value));

            valueColumn.setCellValueFactory(cellData -> {
                int columnIndex = tableView.getColumns().indexOf(valueColumn);
                List<String> rowValues = cellData.getValue();
                if (columnIndex < rowValues.size()) {
                    return new javafx.beans.property.SimpleStringProperty(rowValues.get(columnIndex));
                } else {
                    return new javafx.beans.property.SimpleStringProperty("");
                }
            });

            tableView.getColumns().add(valueColumn);
        }
    }



	public TableView<List<String>> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<List<String>> tableView) {
		this.tableView = tableView;
	}

	public void setComboBox(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public void setLayout(VBox layout) {
		this.layout = layout;
	}

	public Label getPocetnaFunkcijaLabel() {
		return pocetnaFunkcijaLabel;
	}

	public void setPocetnaFunkcijaLabel(Label pocetnaFunkcijaLabel) {
		this.pocetnaFunkcijaLabel = pocetnaFunkcijaLabel;
	}

	public Label getIzlaznaFunkcijaLabel() {
		return izlaznaFunkcijaLabel;
	}

	public void setIzlaznaFunkcijaLabel(Label izlaznaFunkcijaLabel) {
		this.izlaznaFunkcijaLabel = izlaznaFunkcijaLabel;
	}

	public Label getPocetnaFunkcijaValueLabel() {
		return pocetnaFunkcijaValueLabel;
	}

	public void setPocetnaFunkcijaValueLabel(Label pocetnaFunkcijaValueLabel) {
		this.pocetnaFunkcijaValueLabel = pocetnaFunkcijaValueLabel;
	}

	public Label getIzlaznaFunkcijaValueLabel() {
		return izlaznaFunkcijaValueLabel;
	}

	public void setIzlaznaFunkcijaValueLabel(Label izlaznaFunkcijaValueLabel) {
		this.izlaznaFunkcijaValueLabel = izlaznaFunkcijaValueLabel;
	}
    

}
