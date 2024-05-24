package application;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Implicant {
    private SimpleListProperty<Integer> vrednostImplikante;

    public Implicant(Integer... vrednostImplikante) {
        this.vrednostImplikante = new SimpleListProperty<>(FXCollections.observableArrayList(vrednostImplikante));
    }

    public ObservableList<Integer> getVrednostImplikante() {
        return vrednostImplikante.get();
    }

    public void setVrednostImplikante(Integer... vrednostImplikante) {
        this.vrednostImplikante.set(FXCollections.observableArrayList(vrednostImplikante));
    }

    public SimpleListProperty<Integer> vrednostImplikanteProperty() {
        return vrednostImplikante;
    }

    @Override
    public String toString() {
        return vrednostImplikante.get().toString();
    }
}
