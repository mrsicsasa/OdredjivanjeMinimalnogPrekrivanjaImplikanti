package controller;

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

import model.DobavljeniPodaci;
import model.Implicant;

public class DataController {
	
	private final String filePath = Paths.get("src", "data", "data.csv").toString();
	
	
	public DataController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void writeToCSVFile(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true za dodavanje na kraj fajla
            writer.write(text);
            writer.newLine(); // Dodavanje novog reda
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
//	public DobavljeniPodaci readFromCSVFile() {
//		DobavljeniPodaci dp = new DobavljeniPodaci();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(","); // Razdvajanje CSV vrednosti
//                for (String value : values) {
//                    System.out.print(value + " ");
//                }
//                System.out.println();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dp;
//    }
	
	public DobavljeniPodaci readFromCSVFile() throws IOException {
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
	        Stream<Implicant> implicantStream = Arrays.stream(implicantValues)
	            .map(this::parseImplicant); // Parse each String value within the stream

	        ArrayList<Implicant> implikante = implicantStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

	        // Get ulaznaFunkcija from the second line
	        String ulaznaFunkcija = lines.get(1);

	        return new DobavljeniPodaci(implikante, ulaznaFunkcija);
	    }
	}

	// Helper method to parse a String into an Implicant object
	private Implicant parseImplicant(String values) {
	    String[] parts = values.split(","); // Split by comma delimiter
	    String variable = parts[0]; // First part is the variable

	    // Remaining parts are literals
	    List<Integer> literals = Arrays.stream(parts)
	        .skip(1) // Skip the variable name
	        .map(Integer::parseInt) // Convert strings to integers
	        .collect(Collectors.toList());

	    return new Implicant(variable, literals);
	}

}
