package model;

import java.util.List;

public class Implikanta {
    private String funkcija;
    private List<Integer> poklapanja;

    public Implikanta(String funkcija, List<Integer> poklapanja) {
        this.funkcija = funkcija;
        this.poklapanja = poklapanja;
    }

    public String getFunkcija() {
        return funkcija;
    }

    public void setFunkcija(String funkcija) {
        this.funkcija = funkcija;
    }

    public List<Integer> getPoklapanja() {
        return poklapanja;
    }

    public void setPoklapanja(List<Integer> poklapanja) {
        this.poklapanja = poklapanja;
    }

	@Override
	public String toString() {
		return "Implikant [funkcija=" + funkcija + ", poklapanja=" + poklapanja + "]";
	}
}

