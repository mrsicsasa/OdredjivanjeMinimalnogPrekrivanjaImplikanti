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

import model.Implikant;

public class DobavljeniPodaci {
	
	
	private final String filePath = Paths.get("src", "data", "data.csv").toString();

	public void writeToCSVFile(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true za dodavanje na kraj fajla
            writer.write(text);
            writer.newLine(); // Dodavanje novog reda
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public ArrayList<Implikant> getImplikante() throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        // Read lines from the file
	        List<String> lines = reader.lines().collect(Collectors.toList());

	        // Check if there are at least two lines (Implicants and ulaznaFunkcija)
	        if (lines.size() < 2) {
	            throw new IOException("CSV file must contain at least two lines (Implicants and ulaznaFunkcija)");
	        }

	        // Parse Implicants from the first line
	        String[] implicantValues = lines.get(0).split("\\|"); // Split by pipe delimiter

	        // Option B: Process stream of String arrays directly (recommended)
	        Stream<Implikant> implicantStream = Arrays.stream(implicantValues)
	            .map(this::parseImplicant); // Parse each String value within the stream

	        ArrayList<Implikant> implikante = implicantStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	        
	        return implikante;
	    }
	}
	
	public String getUlaznaFunkcija() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        // Read lines from the file
	        List<String> lines = reader.lines().collect(Collectors.toList());

	        // Check if there are at least two lines (Implicants and ulaznaFunkcija)
	        if (lines.size() < 2) {
	            throw new IOException("CSV file must contain at least two lines (Implicants and ulaznaFunkcija)");
	        }

	        // Parse Implicants from the first line
	        String[] implicantValues = lines.get(0).split("\\|"); // Split by pipe delimiter
	        
	        return lines.get(1);
		}
	}

	// Helper method to parse a String into an Implicant object
	private Implikant parseImplicant(String values) {
	    String[] parts = values.split(","); // Split by comma delimiter
	    String variable = parts[0]; // First part is the variable

	    // Remaining parts are literals
	    List<Integer> literals = Arrays.stream(parts)
	        .skip(1) // Skip the variable name
	        .map(Integer::parseInt) // Convert strings to integers
	        .collect(Collectors.toList());

	    return new Implikant(variable, literals);
	}
	
}