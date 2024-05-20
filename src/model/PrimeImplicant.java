package model;

import java.util.List;

public class PrimeImplicant {
private String variables;
private List<Integer> implicants;
public PrimeImplicant(String variables, List<Integer> implicants) {
	super();
	this.variables = variables;
	this.implicants = implicants;
}
public String getVariables() {
	return variables;
}
public void setVariables(String variables) {
	this.variables = variables;
}
public List<Integer> getImplicants() {
	return implicants;
}
public void setImplicants(List<Integer> implicants) {
	this.implicants = implicants;
}
@Override
public String toString() {
	return "PrimeImplicant [variables=" + variables + ", implicants=" + implicants + "]";
}

}
