package state;

import controller.Komponenta6Kontroler;
import interfaces.State;

public class InicijalniState implements State{

	@Override
	public void displayResult(Komponenta6Kontroler controller) {
		controller.setCurrentState(new ProjektantskiState());
		
	}

}
