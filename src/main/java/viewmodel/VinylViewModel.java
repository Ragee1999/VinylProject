package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Vinyl;

public class VinylViewModel {

    private final ObservableList<Vinyl> vinylList = FXCollections.observableArrayList(
            new Vinyl("Title 1", "Artist 1", 2020, "Available"),
            new Vinyl("Title 2", "Artist 2", 2018, "Borrowed"),
            new Vinyl("Title 3", "Artist 3", 2019, "Reserved"),
            new Vinyl("Title 4", "Artist 4", 2010, "Available"),
            new Vinyl("Title 5", "Artist 5", 2015, "Borrowed"),
            new Vinyl("Title 6", "Artist 6", 2016, "Reserved")
    );

    public ObservableList<Vinyl> getVinylList() {
        return vinylList;
    }

    public void markVinylForRemoval(Vinyl vinyl) {
        // Check if the vinyl is in an "Available" state and remove it immediately.
        if ("Available".equals(vinyl.lendingStateProperty().get())) {
            removeVinylImmediately(vinyl);
        } else {
            // For "Reserved" or other states, mark it for removal.
            vinyl.markForRemoval();
        }
    }

    private void removeVinylImmediately(Vinyl vinyl) {
        vinylList.remove(vinyl);
    }
}
