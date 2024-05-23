package controller;

import model.AppModel;
import view.MainView;

public class MainController {
    private AppModel model;
    private MainView view;

    public MainController(AppModel model, MainView view) {
        this.model = model;
        this.view = view;

        // Add event handler
        view.getPocniButton().setOnAction(e -> handlePocniButton());
    }

    public void handlePocniButton() {
        model.updateExitFunction();
    }
}
