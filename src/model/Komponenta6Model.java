package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class Komponenta6Model {
	private String pocetnaFunkcija;
    private ArrayList<Implikant> implikanti;
    private ObservableList<List<String>> redoviTabele;
    private boolean edukativnoPopunjavanjeTabeleZavrseno;
    private String zavrsnaFunkcijaEdukacioniRezim;
    private DobavljeniPodaci dp = new DobavljeniPodaci();
    public String esencijalne="";

    public Komponenta6Model() {
    	this.getPodaci();
//    	this.implikanti = new ArrayList<Implikant>();
        this.edukativnoPopunjavanjeTabeleZavrseno=false;
        this.zavrsnaFunkcijaEdukacioniRezim="";
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

    public ArrayList<Implikant> getImplikanti() {
        return implikanti;
    }
    

    public void setImplikanti(ArrayList<Implikant> implikanti) {
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
