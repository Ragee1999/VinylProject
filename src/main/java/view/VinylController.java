package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.LendingState;
import model.Simulation;
import model.User;
import model.Vinyl;
import viewmodel.VinylViewModel;


public class VinylController {

    @FXML
    private TableView<Vinyl> vinylTableView;
    @FXML
    private TableColumn<Vinyl, String> titleColumn;
    @FXML
    private TableColumn<Vinyl, LendingState> lendingStateColumn;
    @FXML
    private TableColumn<Vinyl, Integer> releaseYearColumn;
    @FXML
    private TableColumn<Vinyl, String> artistColumn;
    @FXML
    private TableColumn<Vinyl, User> lastUserColumn;


    private final VinylViewModel viewModel;


    public VinylController() {
        this.viewModel = new VinylViewModel(new User("User 1"));
    }

    @FXML
    public void initialize() {

        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        releaseYearColumn.setCellValueFactory(cellData -> cellData.getValue().releaseYearProperty().asObject());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        lendingStateColumn.setCellValueFactory(cellData -> cellData.getValue().lendingStateProperty());
        lastUserColumn.setCellValueFactory(cellData -> {
            Vinyl vinyl = cellData.getValue();
            StringProperty lastUserNameProperty = vinyl.lastUserNameProperty();
            return Bindings.createObjectBinding(() -> {
                String lastUserName = lastUserNameProperty.get();
                if (lastUserName == null || lastUserName.isEmpty()) {
                    return new User("No user");
                } else {
                    return new User(lastUserName);
                }
            }, lastUserNameProperty);
        });

        vinylTableView.setItems(viewModel.getVinyls());
        viewModel.loadVinyls();
        Simulation.simulateActions(viewModel.getVinyls());
    }

    @FXML
    public void borrowAction() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        viewModel.borrowAction(selectedVinyl);
    }

    @FXML
    public void returnAction() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        viewModel.returnAction(selectedVinyl);
    }

    @FXML
    public void reserveAction() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        viewModel.reserveAction(selectedVinyl);
    }
}



