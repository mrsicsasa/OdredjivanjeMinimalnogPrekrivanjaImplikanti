package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.AppModel;
import model.Implicant;

public class QuineMcCluskeyController {
    private List<Implicant> implicants;
    private List<Integer> minterms;

    // Find essential implicants
    public List<String> findEssentialImplicants() {
        List<String> essentialImplicants = new ArrayList<>();
        List<Implicant> remainingImplicants = new ArrayList<>(implicants);

        for (Integer minterm : minterms) {
            List<Implicant> coveringImplicants = implicants.stream()
                    .filter(implicant -> implicant.getImplicants().contains(minterm))
                    .collect(Collectors.toList());

            if (coveringImplicants.size() == 1) {
                Implicant essential = coveringImplicants.get(0);
                String essentialVariables = essential.getVariables();
                essentialImplicants.add(essentialVariables);
                remainingImplicants.remove(essential);
            }
        }
        implicants = new ArrayList<>(remainingImplicants);
        return essentialImplicants;
    }

    // Combine essential implicants into a single equation
    public String combineImplicants(List<String> essentialImplicants) {
        Set<String> uniqueImplicants = new HashSet<>(essentialImplicants);
        return String.join(" + ", uniqueImplicants);
    }

    // Set prime implicants
    public void setImplicants(List<Implicant> implicants) {
        this.implicants = implicants;
    }

    // Set minterms
    public void setMinterms(List<Integer> minterms) {
        this.minterms = minterms;
    }
    
    public void updateExitFunction(AppModel model) {


		List<Implicant> implicants = new ArrayList<>();
		implicants.add(new Implicant("zw'", List.of(2, 6, 10, 14, 15)));
		implicants.add(new Implicant("xy'", List.of(8, 9, 10, 11)));
		implicants.add(new Implicant("xz", List.of(10, 11, 14, 15)));

		Implicant initialImplicant = new Implicant("xyz`w` + xyz`w", List.of(2, 6, 8, 9, 10, 11, 14, 15));
		model.setEntryFunction(initialImplicant.getVariables());
		this.setImplicants(implicants);
		this.setMinterms(initialImplicant.getImplicants());

		List<String> essentialImplicants = this.findEssentialImplicants();
		String combinedImplicants = this.combineImplicants(essentialImplicants);

		model.setExitFunction(combinedImplicants);
	}
}
