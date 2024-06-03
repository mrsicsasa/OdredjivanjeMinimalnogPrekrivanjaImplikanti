package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Label;
import model.EducationalEssentialModel;
import model.Implicant;

public class EducationalModeController {
	
	private EducationalEssentialModel eem = new EducationalEssentialModel();
	
	
	
	public EducationalEssentialModel getEem() {
		return eem;
	}

	public void setEem(EducationalEssentialModel eem) {
		this.eem = eem;
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

    public void addEssentialImplicant(Implicant implicant,Label essentialImplicantsLabel) {
    	Set<Implicant> essentialImplicants = eem.getEssentialImplicants();
        if (essentialImplicants.add(implicant)) { // Dodaje implikantu ako nije već dodata
            updateEssentialImplicantsLabel(essentialImplicantsLabel);
            eem.setEssentialImplicants(essentialImplicants);
        }
    }

    public void addIncorrectImplicant(Implicant implicant) {
    	Set<Implicant> incorrectImplicants = eem.getIncorrectImplicants();
        incorrectImplicants.add(implicant); // Dodaje implikantu u skup pogrešno odabranih
        eem.setIncorrectImplicants(incorrectImplicants);
    }
    
    public void updateEssentialImplicantsLabel(Label essentialImplicantsLabel) {//ovde prepraviti sta se zapisuje,skontati takodje sta se i cita
        StringBuilder sb = new StringBuilder("Esencijalne implikante: ");
        StringBuilder csvSb = new StringBuilder("");
        DataController dc = new DataController();//u kontroleru radi upis i citanje,prilagoditi samo za sta se koriste te metode
        ArrayList<Implicant> essentialImplicantsArray = new ArrayList<Implicant>(eem.getEssentialImplicants());
        for (int i=0;i<essentialImplicantsArray.size();i++) {
        	if(i==(essentialImplicantsArray.size() - 1)) {
        		sb.append(essentialImplicantsArray.get(i).getVariables().toString());
        		csvSb.append(essentialImplicantsArray.get(i).getVariables().toString());

        	}else {
        		sb.append(essentialImplicantsArray.get(i).getVariables().toString()).append("+");
        		csvSb.append(essentialImplicantsArray.get(i).getVariables().toString()).append("+");
        	}
        }
        essentialImplicantsLabel.setText(sb.toString());
        if ((eem.getEssentialImplicants().size() + eem.getIncorrectImplicants().size()) == getGrupeProstihImplikanti().size()) {
            dc.writeToCSVFile("\n" + csvSb.toString());
        }
    }
}
