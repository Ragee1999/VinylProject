package model.states;

import model.LendingState;
import model.Vinyl;

public class ReservedState implements LendingState {
    private Vinyl vinyl;

    public ReservedState(Vinyl vinyl) {
        this.vinyl = vinyl;
    }

    @Override
    public void reserve() {
        System.out.println(vinyl.getTitle() + " is already reserved.");
    }

    @Override
    public void borrow() {
        vinyl.setLendingState(new BorrowedState(vinyl));
    }

    @Override
    public void returnVinyl() {
        System.out.println(vinyl.getTitle() + " can't be returned because it's reserved, not borrowed. Marking as available.");
        vinyl.setLendingState(new AvailableState(vinyl));
    }

    @Override
    public String toString() {
        return "Reserved";
    }
}

