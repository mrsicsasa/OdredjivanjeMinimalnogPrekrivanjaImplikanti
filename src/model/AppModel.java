package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.DataController;
import controller.EducationalModeController;
import controller.QuineMcCluskeyController;

public class AppModel {
	private StringProperty entryFunction = new SimpleStringProperty("");
	private StringProperty exitFunction = new SimpleStringProperty("");
	private StringProperty rezimRada = new SimpleStringProperty("Projektantski");

	public String getEntryFunction() {
		return entryFunction.get();
	}

	public StringProperty entryFunctionProperty() {
		return entryFunction;
	}

	public void setEntryFunction(String entryFunction) {
		this.entryFunction.set(entryFunction);
	}

	public String getExitFunction() {
		return exitFunction.get();
	}

	public StringProperty exitFunctionProperty() {
		return exitFunction;
	}

	public void setExitFunction(String exitFunction) {
		this.exitFunction.set(exitFunction);
	}

	public String getRezimRada() {
		return rezimRada.get();
	}

	public StringProperty rezimRadaProperty() {
		return rezimRada;
	}

	public void setRezimRada(String rezimRada) {
		this.rezimRada.set(rezimRada);
	}

	public void updateExitFunction() {
		QuineMcCluskeyController qmController = new QuineMcCluskeyController();
		DataController dc = new DataController();
		EducationalModeController emc = new EducationalModeController();
		
		List<Implicant> implicants = null;
		try {
			implicants = dc.readFromCSVFile().getImplikante();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Implicant initialImplicant = null;
		List<Integer> uniqueImplicants = new ArrayList<>(emc.getUniqueImplicants());
		try {
			initialImplicant = new Implicant(dc.readFromCSVFile().getUlaznaFunkcija(), uniqueImplicants );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setEntryFunction(initialImplicant.getVariables());
		qmController.setImplicants(implicants);
		qmController.setMinterms(initialImplicant.getImplicants());

		List<String> essentialImplicants = qmController.findEssentialImplicants();
		String combinedImplicants = qmController.combineImplicants(essentialImplicants);

		setExitFunction(combinedImplicants);
	}
}
