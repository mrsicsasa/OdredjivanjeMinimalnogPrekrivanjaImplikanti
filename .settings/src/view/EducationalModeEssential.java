package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Implicant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controller.DataController;
import controller.MainController;

public class EducationalModeEssential implements Mode {
    private TableView<Implicant> tableView;
    private Label explanationLabel;
    private Rectangle statusIndicator;
    private Label messageLabel;
    private Implicant selectedImplicant;
    private Set<Implicant> essentialImplicants = new HashSet<>();
    private Set<Implicant> incorrectImplicants = new HashSet<>();
    private Label essentialImplicantsLabel;

    @Override
    public void updateUI(Pane root, MainController controler) {
        root.getChildren().clear();

        tableView = new TableView<>();

        // Kolona za grupu implikanata
        TableColumn<Implicant, String> implicantColumn = new TableColumn<>("Implikante");
        implicantColumn.setCellValueFactory(data -> {
            Implicant implicant = data.getValue();
            return new SimpleStringProperty(implicant.getVariables() + " " + implicant.getImplicants().toString());
        });
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone
        tableView.getColumns().add(implicantColumn);

        // Dodavanje kolona za jedinstvene implikante
        Set<Integer> uniqueImplicants = getUniqueImplicants();
        for (Integer implicant : uniqueImplicants) {
            TableColumn<Implicant, String> implicantCol = new TableColumn<>(implicant.toString());
            implicantCol.setCellValueFactory(data -> {
                Implicant rowImplicant = data.getValue();
                if (rowImplicant.getImplicants().contains(implicant)) {
                    return new SimpleStringProperty("X");
                } else {
                    return new SimpleStringProperty("");
                }
            });
            implicantCol.setPrefWidth(50); // Podesite širinu kolone
            tableView.getColumns().add(implicantCol);
        }

        // Podesite minimalnu i maksimalnu širinu tabele
        tableView.setMinWidth(600);
        tableView.setMaxWidth(800);

        // Dodavanje vrednosti u tabelu
        tableView.getItems().addAll(getGrupeProstihImplikanti());

        // Dodavanje labele i indikatora
        explanationLabel = new Label("Naucicete kako da minimizirate prekidacku funkciju koriscenjem McCluskey metode.");
        statusIndicator = new Rectangle(100, 20, Color.GRAY);
        messageLabel = new Label("");
        essentialImplicantsLabel = new Label("Esencijalne implikante: ");

        VBox rightPane = new VBox(10, new Label("Objasnjenje:"), explanationLabel, statusIndicator, messageLabel, essentialImplicantsLabel);
        rightPane.setPadding(new Insets(10));

        BorderPane content = new BorderPane();
        content.setCenter(tableView);
        content.setRight(rightPane);

        root.getChildren().add(content);

        // Dodavanje rowFactory za bojanje redova
        tableView.setRowFactory(tv -> new TableRow<Implicant>() {
            @Override
            protected void updateItem(Implicant item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else {
                    if (essentialImplicants.contains(item)) {
                        setDisable(true);
                        setStyle("-fx-background-color: lightgreen;");
                    } else if (incorrectImplicants.contains(item)) {
                        setDisable(true);
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setDisable(false);
                        if (item.equals(selectedImplicant)) {
                            if (isCorrectImplicant(item)) {
                                setStyle("-fx-background-color: lightgreen;");
                            } else {
                                setStyle("-fx-background-color: lightcoral;");
                            }
                        } else {
                            setStyle("");
                        }
                    }
                }
            }
        });

        // Dodavanje listenera za selekciju reda
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedImplicant = newSelection;
            if (newSelection != null) {
                if (isCorrectImplicant(newSelection)) {
                    statusIndicator.setFill(Color.GREEN);
                    messageLabel.setText("Izabrali ste dobru implikantu!\nOvo je tacan odabir esencijalnih implikanti\njer ostale implikante ne prekrivaju sve implikante iz ove grupe.");
                    addEssentialImplicant(newSelection);
                } else {
                    statusIndicator.setFill(Color.RED);
                    messageLabel.setText("Niste izabrali dobru implikantu.\nOvo je netacan odabir esencijalnih implikanti\njer su sve implikante iz ove grupe prekrivene u drugim grupama.");
                    addIncorrectImplicant(newSelection);
                }
            } else {
                statusIndicator.setFill(Color.GRAY); // Resetovanje indikatora kada nema selekcije
                messageLabel.setText("");
            }
            tableView.refresh();
        });
    }

    private Set<Integer> getUniqueImplicants() {
        Set<Integer> uniqueImplicants = new HashSet<>();
        for (Implicant implicant : getGrupeProstihImplikanti()) {
            uniqueImplicants.addAll(implicant.getImplicants());
        }
        return uniqueImplicants;
    }

    private boolean isCorrectImplicant(Implicant implicant) {
        // Logika za proveru da li je izabrana implikanta ispravna
        // Ovo je primer, u stvarnosti treba proveriti prema zadatoj funkciji
        return implicant.getImplicants().contains(11);
    }

    private void addEssentialImplicant(Implicant implicant) {
        if (essentialImplicants.add(implicant)) { // Dodaje implikantu ako nije već dodata
            updateEssentialImplicantsLabel();
        }
    }

    private void addIncorrectImplicant(Implicant implicant) {
        incorrectImplicants.add(implicant); // Dodaje implikantu u skup pogrešno odabranih
    }

    private void updateEssentialImplicantsLabel() {//ovde prepraviti sta se zapisuje,skontati takodje sta se i cita
        StringBuilder sb = new StringBuilder("Esencijalne implikante: ");
        StringBuilder csvSb = new StringBuilder("");
        DataController dc = new DataController();//u kontroleru radi upis i citanje,prilagoditi samo za sta se koriste te metode
        ArrayList<Implicant> essentialImplicantsArray = new ArrayList<Implicant>(essentialImplicants);
        for (int i=0;i<essentialImplicantsArray.size();i++) {
        	if(i==(essentialImplicantsArray.size() - 1)) {
        		sb.append(essentialImplicantsArray.get(i).getVariables().toString());
        		csvSb.append(essentialImplicantsArray.get(i).getVariables().toString());

        	}else {
        		sb.append(essentialImplicantsArray.get(i).getVariables().toString()).append("+");
        		csvSb.append(essentialImplicantsArray.get(i).getVariables().toString()).append("+");
        	}
        }
        essentialImplicantsLabel.setText(sb.toString());
        if ((essentialImplicants.size() + incorrectImplicants.size()) == this.getGrupeProstihImplikanti().size()) {
            dc.writeToCSVFile("\n" + csvSb.toString());
        }
    }

    public ArrayList<Implicant> getGrupeProstihImplikanti() {
    	ArrayList<Implicant> implicants = new ArrayList<>();
		implicants.add(new Implicant("zw'", List.of(2, 6, 10, 14, 15)));
		implicants.add(new Implicant("xy'", List.of(8, 9, 10, 11)));
		implicants.add(new Implicant("xz", List.of(10, 11, 14, 15)));
        return implicants;
    }
}
