package viewmodel;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Vinyl;
import model.states.AvailableState;

import java.util.function.Consumer;

public class AddVinylViewModel {

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty artist = new SimpleStringProperty();
    private final StringProperty releaseYear = new SimpleStringProperty();
    private final ObservableList<Vinyl> vinylList;

    public AddVinylViewModel(ObservableList<Vinyl> vinylList) {
        this.vinylList = vinylList;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public StringProperty releaseYearProperty() {
        return releaseYear;
    }


    public boolean addVinyl() {
        String title = this.title.get();
        String artist = this.artist.get();
        String releaseYear = this.releaseYear.get();

        if (title == null || title.isEmpty() || artist == null || artist.isEmpty() || releaseYear == null || releaseYear.isEmpty()) {
            System.out.println("All fields are required.");
            return false;
        }

        if (!releaseYear.matches("\\d{4}")) {
            System.out.println("Release year must contain four digits.");
            return false;
        }

        try {
            int year = Integer.parseInt(releaseYear);
            Vinyl newVinyl = new Vinyl(title, artist, year);
            newVinyl.setLendingState(new AvailableState(newVinyl));
            newVinyl.setLastUser(null);

            vinylList.add(newVinyl);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Release year must be a valid number.");
            return false;
        }
    }


    public void addVinylListener(Consumer<Vinyl> listener) {
        vinylList.addListener((ListChangeListener<Vinyl>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(listener);
                }
            }
        });
    }
}