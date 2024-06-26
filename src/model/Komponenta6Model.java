package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import utils.DobavljeniPodaci;

public class Komponenta6Model {
	
	private String pocetnaFunkcija;
    private ArrayList<Implikanta> implikanti;
    private ObservableList<List<String>> redoviTabele;
    private boolean edukativnoPopunjavanjeTabeleZavrseno;
    private String zavrsnaFunkcijaEdukacioniRezim;
    private DobavljeniPodaci dp = new DobavljeniPodaci();
    public String esencijalne="";
    private ArrayList<HashMap<Integer,Boolean>> kliknutiRedovi = new ArrayList<HashMap<Integer,Boolean>>();

    public Komponenta6Model() {
    	this.getPodaci();
        this.edukativnoPopunjavanjeTabeleZavrseno=false;
        this.zavrsnaFunkcijaEdukacioniRezim="";
    }

	public ArrayList<HashMap<Integer, Boolean>> getKliknutiRedovi() {
		return kliknutiRedovi;
	}

	public void setKliknutiRedovi(ArrayList<HashMap<Integer, Boolean>> kliknutiRedovi) {
		this.kliknutiRedovi = kliknutiRedovi;
	}

	public String getEsencijalne() {
		return esencijalne;
	}

	public void setEsencijalne(String esencijalne) {
		this.esencijalne = esencijalne;
	}

	public String getPocetnaFunkcija() {
        return pocetnaFunkcija;
    }

    public void setPocetnaFunkcija(String pocetnaFunkcija) {
        this.pocetnaFunkcija = pocetnaFunkcija;
    }

    public ArrayList<Implikanta> getImplikanti() {
        return implikanti;
    }
    

    public void setImplikanti(ArrayList<Implikanta> implikanti) {
        this.implikanti = implikanti;
    }

	public ObservableList<List<String>> getRedoviTabele() {
		return redoviTabele;
	}

	public void setRedoviTabele(ObservableList<List<String>> redoviTabele) {
		this.redoviTabele = redoviTabele;
	}

	public boolean isEdukativnoPopunjavanjeTabeleZavrseno() {
		return edukativnoPopunjavanjeTabeleZavrseno;
	}

	public void setEdukativnoPopunjavanjeTabeleZavrseno(boolean edukativnoPopunjavanjeTabeleZavrseno) {
		this.edukativnoPopunjavanjeTabeleZavrseno = edukativnoPopunjavanjeTabeleZavrseno;
	}

	public String getZavrsnaFunkcijaEdukacioniRezim() {
		return zavrsnaFunkcijaEdukacioniRezim;
	}

	public void setZavrsnaFunkcijaEdukacioniRezim(String zavrsnaFunkcijaEdukacioniRezim) {
		this.zavrsnaFunkcijaEdukacioniRezim = zavrsnaFunkcijaEdukacioniRezim;
	}

	public DobavljeniPodaci getDp() {
		return dp;
	}

	public void setDp(DobavljeniPodaci dp) {
		this.dp = dp;
	}
	
    public void getPodaci() {
    	try {
			this.implikanti = dp.getImplikante();
			this.pocetnaFunkcija = dp.getUlaznaFunkcija();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
