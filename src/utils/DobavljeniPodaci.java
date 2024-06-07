package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import interfaces.Dobavi;
import model.Implikanta;

public class DobavljeniPodaci implements Dobavi{
	
	
	private final String filePath = Paths.get("src", "data", "data.csv").toString();

	public void writeToCSVFile(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true za dodavanje na kraj fajla
            writer.write(text);
            writer.newLine(); // Dodavanje novog reda
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public ArrayList<Implikanta> getImplikante() throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        List<String> lines = reader.lines().collect(Collectors.toList());

	        if (lines.size() < 2) {
	            throw new IOException("CSV file must contain at least two lines (Implicants and ulaznaFunkcija)");
	        }

	        String[] implicantValues = lines.get(0).split("\\|"); 

	        Stream<Implikanta> implicantStream = Arrays.stream(implicantValues)
	            .map(this::parseImplicant); 

	        ArrayList<Implikanta> implikante = implicantStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	        
	        return implikante;
	    }
	}
	
	public String getUlaznaFunkcija() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        List<String> lines = reader.lines().collect(Collectors.toList());

	        if (lines.size() < 2) {
	            throw new IOException("CSV file must contain at least two lines (Implicants and ulaznaFunkcija)");
	        }

	        String[] implicantValues = lines.get(0).split("\\|");
	        
	        return lines.get(1);
		}
	}

	private Implikanta parseImplicant(String values) {
	    String[] parts = values.split(","); 
	    String variable = parts[0]; 

	    List<Integer> literals = Arrays.stream(parts)
	        .skip(1) 
	        .map(Integer::parseInt) 
	        .collect(Collectors.toList());

	    return new Implikanta(variable, literals);
	}
	
}