package controller;

import model.AppModel;
import view.MainView;

public class Komponenta6Kontroler {
    private AppModel model;
    private MainView view;

    public Komponenta6Kontroler(AppModel model, MainView view) {
        this.model = model;
        this.view = view;

        
        
    }

    public void handlePocniButton() {
        model.updateExitFunction();
    }
}
