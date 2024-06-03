package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.AppModel;
import model.Implicant;
import view.EducationalModeEssentialView;

public class EducationalModeEssentialController {
    private List<Implicant> implicants;
    private AppModel model;
    private EducationalModeEssentialView view;
    private Rectangle statusIndicator;
    private Label messageLabel;
    private Set<Implicant> essentialImplicants;
    private Set<Implicant> incorrectImplicants;
    private Implicant selectedImplicant;

    public EducationalModeEssentialController(AppModel model, EducationalModeEssentialView view) {
        this.model = model;
        this.view = view;
        this.essentialImplicants = new HashSet<>();
        this.incorrectImplicants = new HashSet<>();
        this.statusIndicator = new Rectangle(100, 20, Color.GRAY);
        this.messageLabel = new Label("");
    }

    public void updateUI(Pane root) {
        root.getChildren().clear();
        view.updateUI(root, model);
        // Kolona za grupu implikanata
        TableColumn<Implicant, String> implicantColumn = new TableColumn<>("Implikante");
        implicantColumn.setCellValueFactory(data -> {
            Implicant implicant = data.getValue();
            return new SimpleStringProperty(implicant.getVariables() + " " + implicant.getImplicants().toString());
        });
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone
        view.getTableView().getColumns().add(implicantColumn);

        // Dodavanje kolona za jedinstvene implikante
        Set<Integer> uniqueImplicants = model.getUniqueImplicants();

        for (Integer implicant : uniqueImplicants) {
            TableColumn<Implicant, String> implicantCol = new TableColumn<>(implicant.toString());
            implicantCol.setCellFactory(col -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        setStyle("-fx-background-color: green;");
                    }
                }
            });
            implicantCol.setCellValueFactory(data -> {
                Implicant rowImplicant = data.getValue();
                if (rowImplicant.getImplicants().contains(implicant)) {
                    return new SimpleStringProperty("X");
                } else {
                    return new SimpleStringProperty("");
                }
            });
            implicantCol.setPrefWidth(50); // Podesite širinu kolone
            implicantColumn.setPrefWidth(150); // Podesite širinu kolone
            view.getTableView().getColumns().add(implicantCol);
        }

        // Podesite minimalnu i maksimalnu širinu tabele
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone
        view.getTableView().setMinWidth(600);
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone
        view.getTableView().setMaxWidth(800);

        // Dodavanje vrednosti u tabelu
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone
        view.getTableView().getItems().addAll(model.getImplicants());

        // Dodavanje rowFactory za bojanje redova
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone

        addRowClickListener();
        // Dodajte kod za inicijalno bojanje redova
        view.getTableView().setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Implicant item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else {
                    if (essentialImplicants.contains(item)) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else {
                        setStyle("-fx-background-color: red;");
                    }
                }
            }
        });
    }

    private void addRowClickListener() {
        view.getTableView().setRowFactory(tv -> {
            TableRow<Implicant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                System.out.println("Clicked on row!");
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Implicant clickedItem = row.getItem();
                    if (essentialImplicants.contains(clickedItem)) {
                        row.setStyle("-fx-background-color: lightgreen;");
                    } else {
                        row.setStyle("");
                    }
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
        view.getTableView().refresh();
    }

    private boolean isCorrectImplicant(Implicant implicant) {
        // Logika za proveru da li je izabrana implikanta ispravna
        // Ovo je primer, u stvarnosti treba proveriti prema zadatoj funkciji
        return implicant.getImplicants().contains(11);
    }
	

	private void addIncorrectImplicant(Implicant implicant) {
		incorrectImplicants.add(implicant); // Dodaje implikantu u skup pogrešno odabranih
	}

	private void updateEssentialImplicantsLabel() {
		// Implementacija metode updateEssentialImplicantsLabel()
	}

}
