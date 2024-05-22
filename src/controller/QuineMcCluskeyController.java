package controller;

import java.util.ArrayList;
import java.util.List;

import model.Implicant;

public class QuineMcCluskeyController {
    private ArrayList<Implicant> implicants;
    private List<Integer> minterms;

    // Konstruktor, getteri i setteri

    public String generateInitialEquation() {
        StringBuilder equation = new StringBuilder();
        for (Implicant implicant : implicants) {
            equation.append(implicant.getVariables()).append(" + ");
        }
        return equation.substring(0, equation.length() - 3); // Uklonite poslednji " + "
    }

    public List<String> findEssentialImplicants() {
        List<String> essentialImplicants = new ArrayList<>();
        List<Implicant> nonEssentialImplicants = new ArrayList<>(implicants); // Kopiramo listu implicantata

        for (Integer minterm : minterms) {
            int count = 0;
            String essentialImplicant = null;
            for (Implicant implicant : implicants) {
                if (implicant.getImplicants().contains(minterm)) {
                    count++;
                    essentialImplicant = implicant.getVariables();
                    //    System.out.println(essentialImplicant);
                }
            }
            if (count == 1) {
                essentialImplicants.add(essentialImplicant);
                // Brišemo bitne implicantate iz liste implicantata
                for (Implicant implicant : implicants) {
                    if (implicant.getVariables().equals(essentialImplicant)) {
                        nonEssentialImplicants.remove(implicant);
                        break; // Prekidamo petlju nakon brisanja implicantata
                    }
                }
            }
        }
        implicants = new ArrayList<>(nonEssentialImplicants); // Ažuriramo listu bitno bitnih implicantata
        return essentialImplicants;
    }

    public String combineImplicants(List<String> essentialImplicants) {
        StringBuilder equation = new StringBuilder();
        List<String> addedImplicants = new ArrayList<>(); // Lista za praćenje dodatih implikanata

        for (String implicant : essentialImplicants) {
            if (!addedImplicants.contains(implicant)) { // Provera da li je impikant već dodat
                equation.append(implicant).append(" + ");
                addedImplicants.add(implicant); // Dodaj impikant u listu dodatih implikanata
            }
        }
        return equation.substring(0, equation.length() - 3); // Remove the last " + "
    }

    public void setPrimeImplicants(ArrayList<Implicant> primeImplicants2) {
        implicants = primeImplicants2;
    }

    public void setMinterms(List<Integer> minterms2) {
        minterms = minterms2;
    }
}

