package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Komponenta6Model implements Initializable {
	
    private List<Implikanta> implicants;
    private List<Integer> minterms;
	private Set<Implikanta> essentialImplicants = new HashSet<>();
    private Set<Implikanta> incorrectImplicants = new HashSet<>();
	private StringProperty entryFunction = new SimpleStringProperty("");
	private StringProperty exitFunction = new SimpleStringProperty("");
	private StringProperty rezimRada = new SimpleStringProperty("Projektantski");

    
	public Set<Implikanta> getEssentialImplicants() {
		return essentialImplicants;
	}
	public void setEssentialImplicants(Set<Implikanta> essentialImplicants) {
		this.essentialImplicants = essentialImplicants;
	}
	public Set<Implikanta> getIncorrectImplicants() {
		return incorrectImplicants;
	}
	public void setIncorrectImplicants(Set<Implikanta> incorrectImplicants) {
		this.incorrectImplicants = incorrectImplicants;
	}
    
	public String getEntryFunction() {
		return entryFunction.get();
	}

	public StringProperty entryFunctionProperty() {
		return entryFunction;
	}

	public void setEntryFunction(String entryFunction) {
		this.entryFunction.set(entryFunction);
	}

	public String getExitFunction() {
		return exitFunction.get();
	}

	public StringProperty exitFunctionProperty() {
		return exitFunction;
	}

	public void setExitFunction(String exitFunction) {
		this.exitFunction.set(exitFunction);
	}
	
	
	public Set<Integer> getUniqueImplicants() {
        Set<Integer> uniqueImplicants = new HashSet<>();
        for (Implikanta implicant : getGrupeProstihImplikanti()) {
            uniqueImplicants.addAll(implicant.getImplicants());
        }
        return uniqueImplicants;
    }
    
    public int getNumberOfImplicants() {
    	int number = 0;
    	ArrayList<Implikanta> implicants = getGrupeProstihImplikanti();
    	for (Implikanta i:implicants) {
    		number += i.getImplicants().size();
    	}
    	return number;
    }

    public ArrayList<Implikanta> getGrupeProstihImplikanti() {
        ArrayList<Implikanta> implicants = new ArrayList<>();//Zameniti za dobavljanje iz csv-a
        implicants.add(new Implikanta("zw'", List.of(2, 6, 10, 14, 15)));
        implicants.add(new Implikanta("xy'", List.of(8, 9, 10, 11)));
        implicants.add(new Implikanta("xz", List.of(10, 11, 14, 15)));
        return implicants;
    }
    

    public boolean isCorrectImplicant(Implikanta implicant) {
        // Logika za proveru da li je izabrana implikanta ispravna
        // Ovo je primer, u stvarnosti treba proveriti prema zadatoj funkciji
        return implicant.getImplicants().contains(11);
    }

    public void addEssentialImplicant(Implikanta implicant,Label essentialImplicantsLabel) {
    	Set<Implikanta> essentialImplicants = getEssentialImplicants();
        if (essentialImplicants.add(implicant)) { // Dodaje implikantu ako nije već dodata

            setEssentialImplicants(essentialImplicants);
        }
    }

    public void addIncorrectImplicant(Implikanta implicant) {
    	Set<Implikanta> incorrectImplicants = getIncorrectImplicants();
        incorrectImplicants.add(implicant); // Dodaje implikantu u skup pogrešno odabranih
        setIncorrectImplicants(incorrectImplicants);
    }
    public List<String> findEssentialImplicants() {
        List<String> essentialImplicants = new ArrayList<>();
        List<Implikanta> remainingImplicants = new ArrayList<>(implicants);

        for (Integer minterm : minterms) {
            List<Implikanta> coveringImplicants = implicants.stream()
                    .filter(implicant -> implicant.getImplicants().contains(minterm))
                    .collect(Collectors.toList());

            if (coveringImplicants.size() == 1) {
            	Implikanta essential = coveringImplicants.get(0);
                String essentialVariables = essential.getVariables();
                essentialImplicants.add(essentialVariables);
                remainingImplicants.remove(essential);
            }
        }
        implicants = new ArrayList<>(remainingImplicants);
        return essentialImplicants;
    }
    
    public String combineImplicants(List<String> essentialImplicants) {
        Set<String> uniqueImplicants = new HashSet<>(essentialImplicants);
        return String.join(" + ", uniqueImplicants);
    }
    
    public void canculateExitFunction() {
    	List<Implikanta> implicants = new ArrayList<>();
		implicants.add(new Implikanta("zw'", List.of(2, 6, 10, 14, 15)));
		implicants.add(new Implikanta("xy'", List.of(8, 9, 10, 11)));
		implicants.add(new Implikanta("xz", List.of(10, 11, 14, 15)));

		Implikanta initialImplicant = new Implikanta("xyz`w` + xyz`w", List.of(2, 6, 8, 9, 10, 11, 14, 15));
		setEntryFunction(initialImplicant.getVariables());
		this.setImplicants(implicants);
		setMinterms(initialImplicant.getImplicants());

		List<String> essentialImplicants = findEssentialImplicants();
		String combinedImplicants = combineImplicants(essentialImplicants);

		setExitFunction(combinedImplicants);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public String getRezimRada() {
		return rezimRada.get();
	}
	public void setRezimRada(StringProperty rezimRada) {
		this.rezimRada = rezimRada;
	}
	public List<Implikanta> getImplicants() {
		return implicants;
	}
	public void setImplicants(List<Implikanta> implicants) {
		this.implicants = implicants;
	}
	public List<Integer> getMinterms() {
		return minterms;
	}
	public void setMinterms(List<Integer> minterms) {
		this.minterms = minterms;
	}

}
