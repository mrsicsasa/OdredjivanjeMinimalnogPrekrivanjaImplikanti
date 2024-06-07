package state;
import controller.Komponenta6Kontroler;
import interfaces.State;

public class ProjektantskiState implements State {
	 @Override
	    public void displayResult(Komponenta6Kontroler controller) {
	        controller.getView().setTableColumns(controller.getAllUniqueValues());
	        controller.getView().updateImplikanti(controller.getDataForTable(""));
	        controller.getView().getPocetnaFunkcijaValueLabel().setText(controller.getModel().getPocetnaFunkcija());
	        controller.getView().getIzlaznaFunkcijaValueLabel().setText(controller.combineImplicants(controller.findEssentialImplicants()));
	    }
}