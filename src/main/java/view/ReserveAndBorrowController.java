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
    private TableColumn<ReserveAndBorrow, Integer> vinylIdColumn;

    @FXML
    private TableColumn<ReserveAndBorrow, Boolean> ReservedColumn;

    @FXML
    private TableColumn<ReserveAndBorrow, Boolean> BorrowedColumn;

    @FXML
    private TableColumn<ReserveAndBorrow, Boolean> AvailableColumn;

    private final ReserveAndBorrowViewModel viewModel = new ReserveAndBorrowViewModel();

    @FXML
    public void initialize() {
        ReserveAndBorrowTableView.setItems(viewModel.getReserveAndBorrowsList());
        vinylIdColumn.setCellValueFactory(cellData -> cellData.getValue().vinylIdProperty().asObject());
        ReservedColumn.setCellValueFactory(cellData -> cellData.getValue().reservedProperty());
        BorrowedColumn.setCellValueFactory(cellData -> cellData.getValue().borrowedProperty());
        AvailableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
    }
}
