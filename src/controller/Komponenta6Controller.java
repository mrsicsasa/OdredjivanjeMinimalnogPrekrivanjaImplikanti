package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.EdukativniState;
import model.Implikant;
import model.Komponenta6Model;
import model.ProjektantskiState;
import model.State;
import view.Komponenta6View;

public class Komponenta6Controller {
    private Komponenta6Model model;
    private Komponenta6View view;
    private State currentState;
    

    public Komponenta6Controller(Komponenta6Model model, Komponenta6View view) {
        this.model = model;
        this.view = view;
        this.currentState = new ProjektantskiState(); // default state

        // Setovanje akcije za ComboBox
        view.getComboBox().setOnAction(e -> {
        	this.view.getIzlaznaFunkcijaValueLabel().setText("");
            String selectedState = view.getComboBox().getValue();
            if (selectedState.equals("Projektantski")) {
                setState(new ProjektantskiState());

            } else if (selectedState.equals("Edukativni")) {
                setState(new EdukativniState());
                this.model.setKliknutiRedovi(new ArrayList<HashMap<Integer,Boolean>>());
    		 	this.model.setEdukativnoPopunjavanjeTabeleZavrseno(false);
    		 	this.model.setZavrsnaFunkcijaEdukacioniRezim("");
                updateView();
            }
            model.setRedoviTabele(getDataForTable(""));
            updateView();
        });

        initialize(); // Dodajemo inicijalizaciju za klik na ćelije

        updateView();
    }
    public ObservableList<List<String>> dobaviPodatkeOTabeli(){
    	return model.getRedoviTabele();
    }
    public void setState(State state) {
        this.currentState = state;
    }

    public void updateView() {
        currentState.displayResult(this);
    }

    public void setModelData(String data) {
        model.setPocetnaFunkcija(data);
    }

    public String getModelData() {
        return model.getPocetnaFunkcija();
    }

    public ArrayList<Implikant> getImplikanti() {
        return model.getImplikanti();
    }

    // Metoda za dobijanje svih jedinstvenih vrednosti iz implikanata
    public Set<Integer> getAllUniqueValues() {
        Set<Integer> uniqueValues = new HashSet<>();
        for (Implikant implikant : model.getImplikanti()) {
            uniqueValues.addAll(implikant.getPoklapanja());
        }
        return uniqueValues;
    }

    // Metoda za dobijanje podataka za svaku jedinstvenu vrednost iz implikanata
    public ObservableList<List<String>> getDataForTable(String tip) {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        Set<Integer> uniqueValues = getAllUniqueValues();
        for (Implikant implikant : model.getImplikanti()) {
            List<String> rowData = new ArrayList<>();
            rowData.add(implikant.getFunkcija() + " " + implikant.getPoklapanja());
            for (Integer value : uniqueValues) {
                if (currentState instanceof ProjektantskiState || tip=="Dobavi") {
                    rowData.add(implikant.getPoklapanja().contains(value) ? "X" : "");
                } else if (currentState instanceof EdukativniState) {
                    rowData.add("");
                }
            }
            data.add(rowData);
        }
      
        return data;
    }

    public void initialize() {
        Set<Integer> poklapanja = getAllUniqueValues();
        model.setEsencijalne(combineImplicants(findEssentialImplicants()));

        view.getTableView().setOnMouseClicked(e -> {
            if (currentState instanceof EdukativniState && !model.isEdukativnoPopunjavanjeTabeleZavrseno()) {
                TablePosition cell = view.getTableView().getSelectionModel().getSelectedCells().get(0);
                int rowIndex = cell.getRow();
                int colIndex = cell.getColumn();

                if (colIndex > 0) {
                    // Ako je kliknuto na ćeliju koja nije u prvoj koloni
                    List<String> rowData = view.getTableView().getItems().get(rowIndex);
                    String[] initialValue = rowData.get(0).split(" ");
                    String value = initialValue[0]; // Prva kolona sadrži funkciju
                    Implikant selectedImplicant = model.getImplikanti().stream()
                            .filter(implikant -> implikant.getFunkcija().equals(value))
                            .findFirst().orElse(null);

                    Integer poklapanjeValue = new ArrayList<>(poklapanja).get(colIndex - 1);
                    if (selectedImplicant != null && selectedImplicant.getPoklapanja().contains(poklapanjeValue)) {
                        // Ako je kliknuto na ispravnu ćeliju
                        if (rowData.get(colIndex).equals("")) {
                            // Ako je ćelija prazna, postavi "X"
                            rowData.set(colIndex, "X");
                            updateView();
                            checkIfAllXAdded();
                            if (model.isEdukativnoPopunjavanjeTabeleZavrseno()) {
                                showModalDialog("Gotovo. Izaberite esencijalne");
                            }
                        }
                    } else {
                        // Ako je kliknuto na neispravnu ćeliju
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Netacno postavljen znak 'X'. Ova implikanta ne pripada izabranoj grupi implikanata.", ButtonType.OK);
                        alert.showAndWait();
                    }
                } else {
                    // Ako je kliknuto na ćeliju u prvoj koloni, iskačemo dijalog s porukom
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Nije dozvoljeno kliknuti na ovu ćeliju!", ButtonType.OK);
                    alert.showAndWait();
                }
            } else if (currentState instanceof EdukativniState && model.isEdukativnoPopunjavanjeTabeleZavrseno()) {
                int rowIndex = view.getTableView().getSelectionModel().getSelectedIndex();
                if (rowIndex >= 0) {
                    List<String> rowData = view.getTableView().getItems().get(rowIndex);
                    String[] initialValue = rowData.get(0).split(" ");
                    String value = initialValue[0];
                    if (model.getEsencijalne().contains(value)) {
                        view.getTableView().getItems().get(rowIndex).set(0, value);
                        model.setEsencijalne(model.getEsencijalne().replaceAll(value, ""));
                        String plus = model.getZavrsnaFunkcijaEdukacioniRezim().isEmpty() ? "" : "+";
                        model.setZavrsnaFunkcijaEdukacioniRezim(model.getZavrsnaFunkcijaEdukacioniRezim() + plus + value);
                        view.getTableView().refresh();
                        updateView();
                        System.out.println(rowIndex);
                        HashMap<Integer, Boolean> map = new HashMap<>();
                        map.put(rowIndex, true);
                        model.getKliknutiRedovi().add(map);
                        applyRowColorAndDisable();
                    }else {
                    	System.out.println(rowIndex);
                    	HashMap<Integer, Boolean> map = new HashMap<>();
                        map.put(rowIndex, false);
                        model.getKliknutiRedovi().add(map);
                        applyRowColorAndDisable();

                    }
                }
            }
        });

    }

    private void applyRowColorAndDisable() {
    	System.out.println(model.getKliknutiRedovi());
        // Postavljamo rowFactory za tabelu samo jednom
        view.getTableView().setRowFactory(tv -> new TableRow<List<String>>() {
            @Override
            protected void updateItem(List<String> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                    setDisable(false);
                } else {
                    int currentIndex = getIndex();
                    boolean isRowDisabled = false;

                    // Proveravamo da li je trenutni red prisutan u listi tfrows i uzimamo njegov status
                    for (HashMap<Integer, Boolean> map : model.getKliknutiRedovi()) {
                        if (map.containsKey(currentIndex)) {
                            boolean isGoodCondition = map.get(currentIndex);
                            if (isGoodCondition) {
                                setStyle("-fx-background-color: lightgreen;");
                            } else {
                                setStyle("-fx-background-color: lightcoral;");
                            }
                            setDisable(true);
                            isRowDisabled = true;
                            break; // Pronašli smo red, nema potrebe dalje tražiti
                        }
                    }

                    if (!isRowDisabled) {
                        setStyle("");
                        setDisable(false);
                    }
                }
            }
        });
    }






    










    
    public List<String> findEssentialImplicants() {
        List<String> essentialImplicants = new ArrayList<>();
        List<Implikant> remainingImplicants = new ArrayList<>(model.getImplikanti());
        
        for (Integer minterm : getAllUniqueValues()) {
            List<Implikant> coveringImplicants = model.getImplikanti().stream()
                    .filter(implicant -> implicant.getPoklapanja().contains(minterm))
                    .collect(Collectors.toList());

            if (coveringImplicants.size() == 1) {
                Implikant essential = coveringImplicants.get(0);
                String[] essentialVariablesInitial = essential.getFunkcija().split(" ");
                String essentialVariables = essentialVariablesInitial[0];
                essentialImplicants.add(essentialVariables);
                remainingImplicants.remove(essential);
            }
        }
       
        return essentialImplicants;
    }
    public String combineImplicants(List<String> essentialImplicants) {
        Set<String> uniqueImplicants = new HashSet<>(essentialImplicants);
        return String.join(" + ", uniqueImplicants);
    }
    private void checkIfAllXAdded() {
        boolean allXAdded = false;
        if(getDataForTable("Dobavi").equals(model.getRedoviTabele())) {
        	allXAdded=true;
        }
        if (allXAdded) {
            
           model.setEdukativnoPopunjavanjeTabeleZavrseno(true);;
        }
        
    }
    

    // Prikaži modalni dijalog
    private void showModalDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

	public Komponenta6Model getModel() {
		return model;
	}

	public void setModel(Komponenta6Model model) {
		this.model = model;
	}

	public Komponenta6View getView() {
		return view;
	}

	public void setView(Komponenta6View view) {
		this.view = view;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
    
}
