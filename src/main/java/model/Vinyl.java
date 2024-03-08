package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;


public class Vinyl {
    private final StringProperty title;
    private final StringProperty artist;
    private final IntegerProperty releaseYear;
    private final StringProperty lendingState;
    private final BooleanProperty markedForRemoval;

    public Vinyl(String title, String artist, int releaseYear, String lendingState) {
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.releaseYear = new SimpleIntegerProperty(releaseYear);
        this.lendingState = new SimpleStringProperty(lendingState);
        this.markedForRemoval = new SimpleBooleanProperty(false);
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

    public BooleanProperty markedForRemovalProperty() {
        return markedForRemoval;
    }

    public void markForRemoval() {
        if ("Available".equals(lendingState.get()) || "Reserved".equals(lendingState.get())) {
            markedForRemoval.set(true);
        }
    }

    public void removeFromLibrary(ObservableList<Vinyl> vinylList) {
        if (markedForRemoval.get()) {
            vinylList.remove(this);
        }
    }

    public boolean isMarkedForRemoval() {
        return markedForRemoval.get();
    }
}
