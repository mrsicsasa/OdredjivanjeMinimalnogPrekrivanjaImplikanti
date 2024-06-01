package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

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
	
	public void readFromCSVFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Razdvajanje CSV vrednosti
                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
