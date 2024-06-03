package view;
import controller.EducationalModeController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import model.AppModel;
import model.Implicant;

import java.util.*;

import interfaces.Mode;



public class EducationalMode implements Mode {
	
	private EducationalModeController edc = new EducationalModeController();
    private TableView<Implicant> tableView;
    private Label explanationLabel;
    private Rectangle statusIndicator;
    private int dodatoX = 0;

    @Override
    public void updateUI(Pane root, AppModel model) {
        root.getChildren().clear();

        tableView = new TableView<>();

        // Kolona za grupu implikanata
        TableColumn<Implicant, String> implicantColumn = new TableColumn<>("Implikante");
        implicantColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVariables() + " " + data.getValue().getImplicants().toString()));
        implicantColumn.setPrefWidth(150); // Podesite širinu kolone
        tableView.getColumns().add(implicantColumn);

        // Dodavanje kolona za jedinstvene implikante
        Set<Integer> uniqueImplicants = edc.getUniqueImplicants();
        for (Integer implicant : uniqueImplicants) {
            TableColumn<Implicant, String> implicantCol = new TableColumn<>(implicant.toString());
            implicantCol.setCellValueFactory(data -> {
                Implicant rowImplicant = data.getValue();
                if (rowImplicant.getImplicants().contains(implicant)) {
                    return new SimpleStringProperty("");
                } else {
                    return new SimpleStringProperty("");
                }
            });
            implicantCol.setPrefWidth(50); // Podesite širinu kolone
            tableView.getColumns().add(implicantCol);

            // Allow clicking to set "X"
            implicantCol.setCellFactory(col -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setOnMouseClicked(event -> {
                            if (getTableRow().getItem() != null) {
                                Implicant rowImplicant = getTableRow().getItem();
                                if (rowImplicant.getImplicants().contains(implicant)) {
                                	if(getText() == "" && getText() != "X") {
                                		statusIndicator.setFill(Color.GREEN);
	                                    setText("X");
	                                    dodatoX += 1;
	                                    if(dodatoX == edc.getNumberOfImplicants()) {
	                                    	showModalDialog("Uspesno ste popunili tabelu. Sada mozete da birate esencijalne implikante.");
	                                    	EducationalModeEssential eme = new EducationalModeEssential();
	                                    	eme.updateUI(root, model);
	                                    }
                                	}
                                } else {
                                	statusIndicator.setFill(Color.RED);
                                    showPopup("Netacno postavljen znak 'X'. Ova implikanta ne pripada izabranoj grupi implikanti.");
                                }
                            }
                        });
                    }
                }
            });
        }

        // Podesite minimalnu i maksimalnu širinu tabele
        tableView.setMinWidth(600);
        tableView.setMaxWidth(800);

        // Dodavanje vrednosti u tabelu
        tableView.getItems().addAll(edc.getGrupeProstihImplikanti());

//        // Dodavanje labele i indikatora
        explanationLabel = new Label("Naucicete kako da popunite tabelu implikanti, da bi kasnije mogli da izaberete esencijalne implikante.");
        statusIndicator = new Rectangle(100, 20, Color.GRAY);

        VBox rightPane = new VBox(10, new Label("Objasnjenje:"), explanationLabel, statusIndicator);
        rightPane.setPadding(new Insets(10));

        BorderPane content = new BorderPane();
        content.setCenter(tableView);
        content.setRight(rightPane);

        root.getChildren().add(content);
    }

    public void showPopup(String message) {
        Popup popup = new Popup();
        Label label = new Label(message);
        label.setStyle("-fx-background-color: lightyellow; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;");
        popup.getContent().add(label);
        popup.setAutoHide(true);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
        popup.show(tableView.getScene().getWindow());
    }
    
	public void showModalDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cestitamo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
