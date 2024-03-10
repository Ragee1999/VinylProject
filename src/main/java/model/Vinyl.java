package model;

import javafx.beans.property.*;
import model.states.*;

public class Vinyl {

    private final StringProperty title = new SimpleStringProperty(this, "title");
    private final StringProperty artist = new SimpleStringProperty(this, "artist");
    private final IntegerProperty releaseYear = new SimpleIntegerProperty(this, "releaseYear");
    private final ObjectProperty<LendingState> lendingState;
    private final StringProperty lastUserName = new SimpleStringProperty("No user");
    private final BooleanProperty markedForRemoval = new SimpleBooleanProperty(false);


    public Vinyl(String title, String artist, int releaseYear) {
        this.title.set(title);
        this.artist.set(artist);
        this.releaseYear.set(releaseYear);
        this.lendingState = new SimpleObjectProperty<>(new AvailableState(this));
        this.lastUserName.set("No user");
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


    public void remove() {
        if (getLendingState() instanceof AvailableState && !markedForRemoval.get()) {
            markedForRemoval.set(true);
            System.out.println(getTitle() + " marked for removal and removed immediately as it is available.");
        } else if ((getLendingState() instanceof ReservedState || getLendingState() instanceof BorrowedState) && !markedForRemoval.get()) {
            markedForRemoval.set(true);
            System.out.println(getTitle() + " marked for removal. It will be removed when it becomes available.");
        } else {
            System.out.println(getTitle() + " is already marked for removal.");
        }
    }

    public BooleanProperty markedForRemovalProperty() {
        return markedForRemoval;
    }
}