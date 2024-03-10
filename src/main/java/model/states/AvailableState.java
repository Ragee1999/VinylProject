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
        System.out.println(vinyl.getTitle() + " is now reserved.");
        vinyl.setLendingState(new ReservedState(vinyl));
    }

    @Override
    public void borrow() {
        System.out.println(vinyl.getTitle() + " is now borrowed.");
        vinyl.setLendingState(new BorrowedState(vinyl));
    }

    @Override
    public void returnVinyl() {
        // Already available
    }

    @Override
    public String toString() {
        return "Available";
    }

}
