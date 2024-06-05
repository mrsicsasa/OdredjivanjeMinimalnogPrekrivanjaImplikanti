package model;

import controller.Komponenta6Controller;

public class ProjektantskiState implements State {
	 @Override
	    public void displayResult(Komponenta6Controller controller) {
	        controller.getView().setTableColumns(controller.getAllUniqueValues());
	        controller.getView().updateImplikanti(controller.getDataForTable(""));
	        controller.getView().getPocetnaFunkcijaValueLabel().setText(controller.getModel().getPocetnaFunkcija());
	        controller.getView().getIzlaznaFunkcijaValueLabel().setText(controller.combineImplicants(controller.findEssentialImplicants()));
	    }
}