package model;

import controller.Komponenta6Controller;


public class EdukativniState implements State {
	 @Override
	    public void displayResult(Komponenta6Controller controller) {
	       
	        controller.getView().setTableColumns(controller.getAllUniqueValues());
	        controller.getView().updateImplikanti(controller.dobaviPodatkeOTabeli());
	        controller.getView().getIzlaznaFunkcijaValueLabel().setText(controller.getModel().getZavrsnaFunkcijaEdukacioniRezim());;

	       
	    }
}
