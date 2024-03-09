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
        System.out.println(vinyl.getTitle() + " is borrowed, but now it's also reserved.");
        vinyl.setLendingState(new ReservedState(vinyl));
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