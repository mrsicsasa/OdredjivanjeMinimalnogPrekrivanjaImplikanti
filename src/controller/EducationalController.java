package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Implicant;

public class EducationalController {
    private Set<Implicant> essentialImplicants = new HashSet<>();
    private Set<Implicant> incorrectImplicants = new HashSet<>();
    
    
    

    public Set<Implicant> getEssentialImplicants() {
		return essentialImplicants;
	}

	public void setEssentialImplicants(Set<Implicant> essentialImplicants) {
		this.essentialImplicants = essentialImplicants;
	}

	public Set<Implicant> getIncorrectImplicants() {
		return incorrectImplicants;
	}

	public void setIncorrectImplicants(Set<Implicant> incorrectImplicants) {
		this.incorrectImplicants = incorrectImplicants;
	}

	public Set<Integer> getUniqueImplicants() {
        Set<Integer> uniqueImplicants = new HashSet<>();
        for (Implicant implicant : getGrupeProstihImplikanti()) {
            uniqueImplicants.addAll(implicant.getImplicants());
        }
        return uniqueImplicants;
    }
    
    public int getNumberOfImplicants() {
    	int number = 0;
    	ArrayList<Implicant> implicants = getGrupeProstihImplikanti();
    	for (Implicant i:implicants) {
    		number += i.getImplicants().size();
    	}
    	return number;
    }

    public ArrayList<Implicant> getGrupeProstihImplikanti() {
        ArrayList<Implicant> implicants = new ArrayList<>();
        implicants.add(new Implicant("zw'", List.of(2, 6, 10, 14, 15)));
        implicants.add(new Implicant("xy'", List.of(8, 9, 10, 11)));
        implicants.add(new Implicant("xz", List.of(10, 11, 14, 15)));
        return implicants;
    }
    
    public boolean isCorrectImplicant(Implicant implicant) {
        // Logika za proveru da li je izabrana implikanta ispravna
        // Ovo je primer, u stvarnosti treba proveriti prema zadatoj funkciji
        return implicant.getImplicants().contains(11);
    }


    public void addIncorrectImplicant(Implicant implicant) {
        incorrectImplicants.add(implicant); // Dodaje implikantu u skup pogrešno odabranih
    }



}
