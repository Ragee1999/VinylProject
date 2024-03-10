package model;

import javafx.beans.property.*;
<<<<<<< HEAD
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
=======
import model.states.AvailableState;

public class Vinyl {

    private final StringProperty title = new SimpleStringProperty(this, "title");
    private final StringProperty artist = new SimpleStringProperty(this, "artist");
    private final IntegerProperty releaseYear = new SimpleIntegerProperty(this, "releaseYear");
    private final ObjectProperty<LendingState> lendingState;
    private final StringProperty lastUserName = new SimpleStringProperty("No user");


    public Vinyl(String title, String artist, int releaseYear) {
        this.title.set(title);
        this.artist.set(artist);
        this.releaseYear.set(releaseYear);
        this.lendingState = new SimpleObjectProperty<>(new AvailableState(this));
        this.lastUserName.set("No user");
>>>>>>> Rageevan
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

    public String getTitle() {
        return title.get();
    }



    public LendingState getLendingState() {
        return lendingState.get();
    }

    public void setLendingState(LendingState newState) {
        this.lendingState.set(newState);
    }

    public void reserve() {
        lendingState.get().reserve();
    }

    public void borrow() {
        lendingState.get().borrow();
    }

    public void returnVinyl() {
        lendingState.get().returnVinyl();
    }

    public ObjectProperty<LendingState> lendingStateProperty() {
        return lendingState;
    }

<<<<<<< HEAD
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
=======
    public StringProperty lastUserNameProperty() {
        return lastUserName;
    }


    public void setLastUser(User user) {
        if (user != null) {
            this.lastUserName.set(user.getName());
        } else {
            this.lastUserName.set("No user");
        }
    }
}
>>>>>>> Rageevan
