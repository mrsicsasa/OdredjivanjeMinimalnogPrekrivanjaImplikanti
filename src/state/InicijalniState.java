package state;

import controller.Komponenta6Controller;
import interfaces.State;

public class InicijalniState implements State{

	@Override
	public void displayResult(Komponenta6Controller controller) {
		controller.setCurrentState(new ProjektantskiState());
		
	}

}
