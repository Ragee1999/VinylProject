package model.states;

import model.LendingState;
import model.Vinyl;

public class AvailableState implements LendingState {
    private Vinyl vinyl;

    public AvailableState(Vinyl vinyl) {
        this.vinyl = vinyl;
    }

    @Override
    public void reserve() {
        if (!vinyl.markedForRemovalProperty().get()) {
            vinyl.setLendingState(new ReservedState(vinyl));
        } else {
            System.out.println(vinyl.getTitle() + " is marked for removal and cannot be reserved.");
        }
    }

    @Override
    public void borrow() {
        vinyl.setLendingState(new BorrowedState(vinyl));
    }

    public void returnVinyl() {
        System.out.println(vinyl.getTitle() + " is already available.");
    }

    @Override
    public String toString() {
        return "Available";
    }

}
