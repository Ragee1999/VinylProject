package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.LendingState;
import model.Simulation;
import model.User;
import model.Vinyl;
import viewmodel.AddVinylViewModel;
import viewmodel.VinylViewModel;

import java.io.IOException;


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
    @FXML
    Button removeButton;


    private final VinylViewModel viewModel;
    private final AddVinylViewModel addVinylViewModel;


    public VinylController() {
        this.viewModel = new VinylViewModel(new User("User 1"));
        this.addVinylViewModel = new AddVinylViewModel(FXCollections.observableArrayList());
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
        removeButton.setOnAction(event -> removeSelectedVinyl());
        addVinylViewModel.addVinylListener(vinyl -> vinylTableView.getItems().add(vinyl));

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

    @FXML
    public void removeSelectedVinyl() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        if (selectedVinyl != null) {
            viewModel.removeVinylIfAvailable(selectedVinyl);
        } else {
            System.out.println("Please select a vinyl to remove.");
        }
    }


    private Stage addVinylStage; // Reference to the add vinyl stage


    public void openAddVinylWindow() {
        if (addVinylStage == null || !addVinylStage.isShowing()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/group6/vinylproject/View/createVinyl.fxml"));
            try {
                Parent root = loader.load();
                AddVinylController addVinylController = loader.getController();
                addVinylController.initialize(addVinylViewModel);

                addVinylStage = new Stage();
                addVinylStage.setScene(new Scene(root));
                addVinylStage.setTitle("Add Vinyl");
                addVinylStage.setOnCloseRequest(event -> addVinylStage = null);
                addVinylStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            addVinylStage.toFront();
        }
    }
}



