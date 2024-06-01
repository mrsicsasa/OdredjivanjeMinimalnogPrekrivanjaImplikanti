package controller;

import model.AppModel;

public class MainController {
    private AppModel model;
    private QuineMcCluskeyController qmc;
    public MainController(AppModel model) {
        this.model = model;
        this.qmc = new QuineMcCluskeyController();
    }

    public AppModel getModel() {
		return model;
	}

	public void setModel(AppModel model) {
		this.model = model;
	}

	public QuineMcCluskeyController getQmc() {
		return qmc;
	}

	public void setQmc(QuineMcCluskeyController qmc) {
		this.qmc = qmc;
	}

	public void handlePocniButton() {
    	qmc.updateExitFunction(model);
    }
}
