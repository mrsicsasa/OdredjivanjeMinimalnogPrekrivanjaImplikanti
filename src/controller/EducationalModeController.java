package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import model.Implicant;

public class EducationalModeController {
	
	
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
    


}
