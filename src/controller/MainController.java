package controller;

import model.AppModel;

public class MainController {
    private AppModel model;
    private QuineMcCluskeyController qmc;
    private EducationalController emc;
    public MainController(AppModel model) {
        this.model = model;
        this.qmc = new QuineMcCluskeyController();
        this.setEmc(new EducationalController());
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

	public EducationalController getEmc() {
		return emc;
	}

	public void setEmc(EducationalController emc) {
		this.emc = emc;
	}
}
