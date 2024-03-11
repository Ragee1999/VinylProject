package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.Vinyl;
import model.states.*;


public class VinylViewModel {

    private final ObservableList<Vinyl> vinyls = FXCollections.observableArrayList();
    private final User currentUser;

    public VinylViewModel(User currentUser) {
        this.currentUser = currentUser;
    }

    public ObservableList<Vinyl> getVinyls() {
        return vinyls;
    }

    public void reserveVinyl(Vinyl vinyl, User user) {
        vinyl.reserve();
        vinyl.setLastUser(user);
    }

    public void borrowVinyl(Vinyl vinyl, User user) {
        vinyl.borrow();
        vinyl.setLastUser(user);
    }

    public void returnVinyl(Vinyl vinyl, User user) {
        vinyl.returnVinyl();
        vinyl.setLastUser(user);
        checkAndRemoveVinyl(vinyl);
    }



    private void checkAndRemoveVinyl(Vinyl vinyl) {
        if (vinyl.markedForRemovalProperty().get() && vinyl.getLendingState() instanceof AvailableState) {
            vinyls.remove(vinyl);
            System.out.println(vinyl.getTitle() + " has been removed from the library.");
        }
    }

    public void removeVinylIfAvailable(Vinyl vinyl) {
        if (vinyl.getLendingState() instanceof AvailableState) {
            vinyls.remove(vinyl);
            System.out.println(vinyl.getTitle() + " has been removed from the library because it was available.");
        } else {
            vinyl.remove();
        }
    }

    public void loadVinyls() {
        vinyls.addAll(
                new Vinyl("Title 1", "Artist A", 1983),
                new Vinyl("Title 2", "Artist B", 1991),
                new Vinyl("Title 3", "Artist C", 2000),
                new Vinyl("Title 4", "Artist D", 2010),
                new Vinyl("Title 5", "Artist E", 2020),
                new Vinyl("Title 6", "Artist C", 2001),
                new Vinyl("Title 7", "Artist G", 2011),
                new Vinyl("Title 8", "Artist H", 2023),
                new Vinyl("Title 9", "Artist I", 1999)
        );
    }

    public void borrowAction(Vinyl selectedVinyl) {
        if (selectedVinyl != null) {
            if (selectedVinyl.getLendingState() instanceof AvailableState) {
                System.out.println("Cannot borrow. Vinyl is not reserved.");
            } else if (selectedVinyl.getLendingState() instanceof ReservedState) {
                borrowVinyl(selectedVinyl, currentUser);
                selectedVinyl.setLastUser(currentUser);
                selectedVinyl.setLendingState(new BorrowedState(selectedVinyl));
            } else {
                System.out.println("Cannot borrow. Vinyl is already borrowed.");
            }
        } else {
            System.out.println("Please select a vinyl to borrow.");
        }
    }

    public void returnAction(Vinyl selectedVinyl) {
        if (selectedVinyl != null) {
            if (selectedVinyl.getLendingState() instanceof BorrowedState) {
                returnVinyl(selectedVinyl, currentUser);
                selectedVinyl.setLastUser(null);
                selectedVinyl.setLendingState(new AvailableState(selectedVinyl));
            } else {
                System.out.println("Cannot return. Vinyl is not borrowed.");
            }
        } else {
            System.out.println("Please select a vinyl to return.");
        }
    }

    public void reserveAction(Vinyl selectedVinyl) {
        if (selectedVinyl != null) {
            if (selectedVinyl.getLendingState() instanceof AvailableState) {
                reserveVinyl(selectedVinyl, currentUser);
                selectedVinyl.setLastUser(currentUser);
                selectedVinyl.setLendingState(new ReservedState(selectedVinyl));
            } else {
                System.out.println("Cannot reserve. Vinyl is not available.");
            }
        } else {
            System.out.println("Please select a vinyl to reserve.");
        }
    }
}
