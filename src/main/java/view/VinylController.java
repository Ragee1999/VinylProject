package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Vinyl;
import viewmodel.VinylViewModel;

public class VinylController {

    @FXML
    private TableView<Vinyl> vinylTableView;

    @FXML
    private TableColumn<Vinyl, String> titleColumn;

    @FXML
    private TableColumn<Vinyl, String> artistColumn;

    @FXML
    private TableColumn<Vinyl, Integer> releaseYearColumn;

    @FXML
    private TableColumn<Vinyl, String> lendingStateColumn;

    private final VinylViewModel viewModel = new VinylViewModel();

    @FXML
    public void initialize() {
        vinylTableView.setItems(viewModel.getVinylList());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        releaseYearColumn.setCellValueFactory(cellData -> cellData.getValue().releaseYearProperty().asObject());
        lendingStateColumn.setCellValueFactory(cellData -> cellData.getValue().lendingStateProperty());
    }

}