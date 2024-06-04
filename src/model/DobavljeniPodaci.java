package model;

import java.util.ArrayList;

public class DobavljeniPodaci {
	
	private ArrayList<Implicant> implikante;
	private String ulaznaFunkcija;
	public DobavljeniPodaci(ArrayList<Implicant> implikante, String ulaznaFunkcija) {
		super();
		this.implikante = implikante;
		this.ulaznaFunkcija = ulaznaFunkcija;
	}
	public DobavljeniPodaci() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Implicant> getImplikante() {
		return implikante;
	}
	public void setImplikante(ArrayList<Implicant> implikante) {
		this.implikante = implikante;
	}
	public String getUlaznaFunkcija() {
		return ulaznaFunkcija;
	}
	public void setUlaznaFunkcija(String ulaznaFunkcija) {
		this.ulaznaFunkcija = ulaznaFunkcija;
	}
	
	public String toString() {
		return "Kreirane instance klase.";
	}
	
}
