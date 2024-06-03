package model;

import java.util.HashSet;
import java.util.Set;

public class EducationalEssentialModel {
	
	private Set<Implicant> essentialImplicants = new HashSet<>();
    private Set<Implicant> incorrectImplicants = new HashSet<>();
	public EducationalEssentialModel(Set<Implicant> essentialImplicants, Set<Implicant> incorrectImplicants) {
		super();
		this.essentialImplicants = essentialImplicants;
		this.incorrectImplicants = incorrectImplicants;
	}
	public EducationalEssentialModel() {
		super();
		// TODO Auto-generated constructor stub
	}
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
    
    
}
