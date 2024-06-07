package state;
import controller.Komponenta6Kontroler;
import interfaces.State;

public class EdukativniState implements State {
	 @Override
	    public void displayResult(Komponenta6Kontroler controller) {
	        controller.getView().setTableColumns(controller.getAllUniqueValues());
	        controller.getView().updateImplikanti(controller.dobaviPodatkeOTabeli());
	        controller.getView().getIzlaznaFunkcijaValueLabel().setText(controller.getModel().getZavrsnaFunkcijaEdukacioniRezim());;

	    }
}
