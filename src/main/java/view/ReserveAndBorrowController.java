package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ReserveAndBorrow;
import viewmodel.ReserveAndBorrowViewModel;


public class ReserveAndBorrowController {

    @FXML
    private TableView<ReserveAndBorrow> ReserveAndBorrowTableView;

    @FXML
    private TableColumn<ReserveAndBorrow, Integer> Vinylid;

    @FXML
    private TableColumn<ReserveAndBorrow, Boolean> Reserved;

    @FXML
    private TableColumn<ReserveAndBorrow, Boolean> Borrowed;

    @FXML
    private TableColumn<ReserveAndBorrow, Boolean> Available;

    private final ReserveAndBorrowViewModel viewModel = new ReserveAndBorrowViewModel();

    @FXML
    public void initialize() {
        ReserveAndBorrowTableView.setItems(viewModel.getReserveAndBorrowsList());
        Vinylid.setCellValueFactory(cellData -> cellData.getValue().vinylIdProperty());  //check ReserveAndBorrow.java
        Reserved.setCellValueFactory(cellData -> cellData.getValue().reservedProperty());
        Borrowed.setCellValueFactory(cellData -> cellData.getValue().borrowedProperty());
        Available.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
    }
}
