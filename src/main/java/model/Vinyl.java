package model;

import javafx.beans.property.*;


public class Vinyl {
    private final StringProperty title;
    private final StringProperty artist;
    private final IntegerProperty releaseYear;
    private final StringProperty lendingState;

    public Vinyl(String title, String artist, int releaseYear, String lendingState) {
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.releaseYear = new SimpleIntegerProperty(releaseYear);
        this.lendingState = new SimpleStringProperty(lendingState);
    }


    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public IntegerProperty releaseYearProperty() {
        return releaseYear;
    }

    public StringProperty lendingStateProperty() {
        return lendingState;
    }
}