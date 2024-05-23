package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
}
