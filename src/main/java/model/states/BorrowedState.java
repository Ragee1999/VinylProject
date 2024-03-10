package model.states;

import model.LendingState;
import model.Vinyl;

public class BorrowedState implements LendingState {
    private Vinyl vinyl;

    public BorrowedState(Vinyl vinyl) {
        this.vinyl = vinyl;
    }

    @Override
    public void reserve() {
        if (!vinyl.markedForRemovalProperty().get()) {
            System.out.println(vinyl.getTitle() + " is now reserved.");
            vinyl.setLendingState(new ReservedState(vinyl));
        } else {
            System.out.println(vinyl.getTitle() + " is marked for removal and cannot be reserved.");
        }
    }


    @Override
    public void borrow() {
        System.out.println(vinyl.getTitle() + " is already borrowed.");
    }

    @Override
    public void returnVinyl() {
        System.out.println(vinyl.getTitle() + " is returned and now available.");
        vinyl.setLendingState(new AvailableState(vinyl));
    }

    @Override
    public String toString() {
        return "Borrowed";
    }
}