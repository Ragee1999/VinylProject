package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.AddVinylViewModel;

public class AddVinylController {

    @FXML
    private TextField addTitle;

    @FXML
    private TextField addArtist;

    @FXML
    private TextField addYear;


    private AddVinylViewModel viewModel;


    public AddVinylController() {
        this.viewModel = new AddVinylViewModel(FXCollections.observableArrayList());
    }

    @FXML

        public void initialize(AddVinylViewModel viewModel) {
            this.viewModel = viewModel;

        // Bind the TextProperty of addTitle to the titleProperty of viewModel
        addTitle.textProperty().bindBidirectional(viewModel.titleProperty());

        // Bind the TextProperty of addArtist to the artistProperty of viewModel
        addArtist.textProperty().bindBidirectional(viewModel.artistProperty());

        // Bind the TextProperty of addYear to the releaseYearProperty of viewModel
        addYear.textProperty().bindBidirectional(viewModel.releaseYearProperty());

    }

    @FXML
    public void confirmVinyl() {
        boolean vinylAdded = viewModel.addVinyl();
        if (vinylAdded) {
            closeWindow();
        }
    }

    @FXML
    public void cancelVinyl() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) addTitle.getScene().getWindow();
        stage.close();
        addTitle.clear();
        addArtist.clear();
        addYear.clear();
    }
}
