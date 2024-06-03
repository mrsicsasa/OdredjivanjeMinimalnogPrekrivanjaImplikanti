package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.AppModel;
import model.Implicant;
import view.EducationalModeEssentialView;
import view.EducationalModeView;
import javafx.scene.layout.Pane;

import java.util.Set;

public class EducationalModeController {
    private AppModel model;
    private EducationalModeView view;
    private int dodatoX = 0;

    public EducationalModeController(AppModel model, EducationalModeView view) {
        this.model = model;
        this.view = view;
    }
    public void updateUI(Pane root) {
        view.updateUI(root, null);
        TableColumn<Implicant, String> implicantColumn = new TableColumn<>("Implikante");
        implicantColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVariables() + " " + data.getValue().getImplicants().toString()));
        implicantColumn.setPrefWidth(150);
        view.getTableView().getColumns().add(implicantColumn);

        Set<Integer> uniqueImplicants = model.getUniqueImplicants();
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
            implicantCol.setPrefWidth(50);
            view.getTableView().getColumns().add(implicantCol);

            implicantCol.setCellFactory(new Callback<>() {
                @Override
                public TableCell<Implicant, String> call(TableColumn<Implicant, String> col) {
                    return new TableCell<>() {
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
                                            if (getText().isEmpty() && !getText().equals("X")) {
                                                view.getStatusIndicator().setFill(Color.GREEN);
                                                setText("X");
                                                dodatoX += 1;
                                                if (dodatoX == model.getNumberOfImplicants()) {
                                                    view.showModalDialog("Uspesno ste popunili tabelu. Sada mozete da birate esencijalne implikante.");
                                                    // Transition to the next educational mode step
                                                    //EducationalModeEssentialView essentialView = new EducationalModeEssentialView();
                                                   // EducationalModeEssentialController essentialController = new EducationalModeEssentialController(model, essentialView);
                                                   //essentialController.updateUI(root);
                                                  //  root.getChildren().clear();
                                                  //  root.getChildren().add(essentialView.getRightPane());
                                                }
                                            }
                                        } else {
                                            view.getStatusIndicator().setFill(Color.RED);
                                            view.showPopup("Netacno postavljen znak 'X'. Ova implikanta ne pripada izabranoj grupi implikanti.");
                                        }
                                    }
                                });
                            }
                        }
                    };
                }
            });
        }

        view.getTableView().getItems().addAll(model.getImplicants());

        view.getTableView().setMinWidth(600);
        view.getTableView().setMaxWidth(800);
    }
}
