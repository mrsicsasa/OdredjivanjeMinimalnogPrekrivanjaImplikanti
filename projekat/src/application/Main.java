package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Implicant> tableView;
    private Label explanationLabel;
    private Rectangle statusIndicator;
    private Label messageLabel;
    private Implicant selectedImplicant;
    private Set<Implicant> essentialImplicants = new HashSet<>();
    private Set<Implicant> incorrectImplicants = new HashSet<>();
    private Label essentialImplicantsLabel;

    public ArrayList<Implicant> getGrupeProstihImplikanti() {
        Implicant i1 = new Implicant(2, 6, 10, 14);
        Implicant i2 = new Implicant(8, 9, 10, 11);
        Implicant i3 = new Implicant(10, 11, 14, 15);
        ArrayList<Implicant> implicants = new ArrayList<>();
        implicants.add(i1);
        implicants.add(i2);
        implicants.add(i3);
        return implicants;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            tableView = new TableView<>();

            // Kolona za grupu implikanata
            TableColumn<Implicant, String> implicantColumn = new TableColumn<>("Grupe implikanti");
            implicantColumn.setCellValueFactory(data -> {
                Implicant implicant = data.getValue();
                return new SimpleStringProperty(implicant.getVrednostImplikante().toString());
            });
            implicantColumn.setPrefWidth(150); // Podesite širinu kolone
            tableView.getColumns().add(implicantColumn);

            // Dodavanje kolona za jedinstvene implikante
            Set<Integer> uniqueImplicants = getUniqueImplicants();
            for (Integer implicant : uniqueImplicants) {
                TableColumn<Implicant, String> implicantCol = new TableColumn<>(implicant.toString());
                implicantCol.setCellValueFactory(data -> {
                    Implicant rowImplicant = data.getValue();
                    if (rowImplicant.getVrednostImplikante().contains(implicant)) {
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
            rightPane.setPadding(new javafx.geometry.Insets(10));

            root.setCenter(tableView);
            root.setRight(rightPane);

            Scene scene = new Scene(root, 1100, 400); // Povećajte širinu scene za bolji prikaz
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Edukativni režim - Minimizacija prekidačke funkcije");
            primaryStage.show();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<Integer> getUniqueImplicants() {
        Set<Integer> uniqueImplicants = new HashSet<>();
        for (Implicant implicant : getGrupeProstihImplikanti()) {
            uniqueImplicants.addAll(implicant.getVrednostImplikante());
        }
        return uniqueImplicants;
    }

    private boolean isCorrectImplicant(Implicant implicant) {
        // Logika za proveru da li je izabrana implikanta ispravna
        // Ovo je primer, u stvarnosti treba proveriti prema zadatoj funkciji
        return implicant.getVrednostImplikante().contains(11);
    }

    private void addEssentialImplicant(Implicant implicant) {
        if (essentialImplicants.add(implicant)) { // Dodaje implikantu ako nije već dodata
            updateEssentialImplicantsLabel();
        }
    }

    private void addIncorrectImplicant(Implicant implicant) {
        incorrectImplicants.add(implicant); // Dodaje implikantu u skup pogrešno odabranih
    }

    private void updateEssentialImplicantsLabel() {
        StringBuilder sb = new StringBuilder("Esencijalne implikante: ");
        for (Implicant implicant : essentialImplicants) {
            sb.append(implicant.getVrednostImplikante().toString()).append(" ");
        }
        essentialImplicantsLabel.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
