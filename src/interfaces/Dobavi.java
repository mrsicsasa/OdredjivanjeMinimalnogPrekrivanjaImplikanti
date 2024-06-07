package interfaces;

import java.io.IOException;
import java.util.ArrayList;

import model.Implikanta;

public interface Dobavi {
	
	void writeToCSVFile(String text);
	ArrayList<Implikanta> getImplikante() throws IOException;
	String getUlaznaFunkcija() throws IOException;
	
}
