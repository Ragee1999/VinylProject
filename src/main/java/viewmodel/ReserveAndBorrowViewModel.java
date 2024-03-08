package viewmodel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ReserveAndBorrow;


public class ReserveAndBorrowViewModel {

    private final ObservableList<ReserveAndBorrow> reserveAndBorrowsList = FXCollections.observableArrayList(
            new ReserveAndBorrow(100, false, false, false),
            new ReserveAndBorrow(100, true, false, false),
            new ReserveAndBorrow(100, true, false, false)




    );

    public ObservableList<ReserveAndBorrow> getReserveAndBorrowsList() {
        return reserveAndBorrowsList;
    }
}





