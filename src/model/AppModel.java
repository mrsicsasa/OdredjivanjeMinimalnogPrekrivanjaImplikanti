package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;
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

}
