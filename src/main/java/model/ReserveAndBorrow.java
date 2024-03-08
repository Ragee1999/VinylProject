package model;

import group6.vinylproject.Main;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.beans.property.*;

import java.io.IOException;

public class ReserveAndBorrow {

    private final IntegerProperty vinylId;
    private final BooleanProperty Reserved;
    private final BooleanProperty Borrowed;
    private final BooleanProperty Available;


    public ReserveAndBorrow(Integer vinylId, boolean reserved, boolean borrowed, boolean available) {
       this.vinylId = new SimpleIntegerProperty(vinylId);
        this.Reserved = new SimpleBooleanProperty(false);
        this.Borrowed = new SimpleBooleanProperty(borrowed);
        this.Available = new SimpleBooleanProperty(false);
    }


    public IntegerProperty vinylIdProperty() {
        return vinylId;
    }

    public BooleanProperty reservedProperty() {
        return Reserved;
    }

    public BooleanProperty borrowedProperty() {
        return Borrowed;
    }

    public BooleanProperty availableProperty() {
        return Available;
    }



}
