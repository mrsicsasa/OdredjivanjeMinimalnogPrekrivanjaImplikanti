package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import model.AppModel;
import model.Implicant;
import model.Mode;

public class EducationalModeView implements Mode{
    private TableView<Implicant> tableView;
    private Label explanationLabel;
    private Rectangle statusIndicator;

	@Override
	public void updateUI(Pane root, AppModel model) {
		 root.getChildren().clear();

	        tableView = new TableView<>();
	        explanationLabel = new Label("Naucicete kako da popunite tabelu implikanti, da bi kasnije mogli da izaberete esencijalne implikante.");
	        statusIndicator = new Rectangle(100, 20, Color.GRAY);

	        VBox rightPane = new VBox(10, new Label("Objasnjenje:"), explanationLabel, statusIndicator);
	        rightPane.setPadding(new Insets(10));

	        BorderPane content = new BorderPane();
	        content.setCenter(tableView);
	        content.setRight(rightPane);

	        root.getChildren().add(content);
	}
    public TableView<Implicant> getTableView() {
        return tableView;
    }

    public Label getExplanationLabel() {
        return explanationLabel;
    }

    public Rectangle getStatusIndicator() {
        return statusIndicator;
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cestitamo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


	}

