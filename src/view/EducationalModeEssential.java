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
import model.AppModel;
import model.Implicant;


import java.util.Set;

import controller.EducationalModeController;
import interfaces.Mode;

public class EducationalModeEssential implements Mode {
	
    private TableView<Implicant> tableView;
    private Label explanationLabel;
    private Rectangle statusIndicator;
    private Label messageLabel;
    private Implicant selectedImplicant;
    private EducationalModeController emc = new EducationalModeController();
    private Label essentialImplicantsLabel;

    @Override
    public void updateUI(Pane root, AppModel model) {
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
        Set<Integer> uniqueImplicants = emc.getUniqueImplicants();
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
        tableView.getItems().addAll(emc.getGrupeProstihImplikanti());

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
                    if (emc.getEem().getEssentialImplicants().contains(item)) {
                        setDisable(true);
                        setStyle("-fx-background-color: lightgreen;");
                    } else if (emc.getEem().getIncorrectImplicants().contains(item)) {
                        setDisable(true);
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setDisable(false);
                        if (item.equals(selectedImplicant)) {
                            if (emc.isCorrectImplicant(item)) {
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
                if (emc.isCorrectImplicant(newSelection)) {
                    statusIndicator.setFill(Color.GREEN);
                    messageLabel.setText("Izabrali ste dobru implikantu!\nOvo je tacan odabir esencijalnih implikanti\njer ostale implikante ne prekrivaju sve implikante iz ove grupe.");
                    emc.addEssentialImplicant(newSelection,essentialImplicantsLabel);
                } else {
                    statusIndicator.setFill(Color.RED);
                    messageLabel.setText("Niste izabrali dobru implikantu.\nOvo je netacan odabir esencijalnih implikanti\njer su sve implikante iz ove grupe prekrivene u drugim grupama.");
                    emc.addIncorrectImplicant(newSelection);
                }
            } else {
                statusIndicator.setFill(Color.GRAY); // Resetovanje indikatora kada nema selekcije
                messageLabel.setText("");
            }
            tableView.refresh();
        });
    }

}
