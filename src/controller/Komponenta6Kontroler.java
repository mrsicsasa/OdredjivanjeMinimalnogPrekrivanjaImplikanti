package controller;

import model.Komponenta6Model;

public class Komponenta6Kontroler {

	private Komponenta6Model model;
	
	public Komponenta6Kontroler(Komponenta6Model model) {
		super();
		this.model = model;
	}

	public void addX() {
		
	}
	
	public String getUlaznaFunkcijaValue() {
		return model.getEntryFunction();
	}

	public String getRezimRadaValue() {
		// TODO Auto-generated method stub
		return model.getRezimRada();
	}

	public String getIzlaznaFunkcijaValue() {
		// TODO Auto-generated method stub
		return model.getExitFunction();
	}

	public String updateExitFunction() {
		System.out.println("Poceo");
		model.canculateExitFunction();
		System.out.println("Zavrsio");
		
		return model.getExitFunction();
	}

	
}
