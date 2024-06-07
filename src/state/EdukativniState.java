package state;

import java.util.ArrayList;
import java.util.HashMap;

import controller.Komponenta6Controller;
import interfaces.State;


public class EdukativniState implements State {
	 @Override
	    public void displayResult(Komponenta6Controller controller) {
	        controller.getView().setTableColumns(controller.getAllUniqueValues());
	        controller.getView().updateImplikanti(controller.dobaviPodatkeOTabeli());
	        controller.getView().getIzlaznaFunkcijaValueLabel().setText(controller.getModel().getZavrsnaFunkcijaEdukacioniRezim());;

	    }
}
