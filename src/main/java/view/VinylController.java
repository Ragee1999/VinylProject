package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.application.Platform;
import model.LendingState;
import model.User;
import model.states.*;
import model.Vinyl;
import viewmodel.VinylViewModel;

import java.util.Random;

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
    private Button removeButton;


    private final VinylViewModel viewModel = new VinylViewModel();
    private final User currentUser = new User("User 1"); // IDE user is always user 1

    private final User user2 = new User("User 2");
    private final User user3 = new User("User 3");
    private final User user4 = new User("User 4");

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
        simulateActions();
        removeButton.setOnAction(event -> removeSelectedVinyl());
    }


    private void simulateActions() {
        Thread simulationThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(2000); // 2 second actions
                    Platform.runLater(() -> {
                        int index = random.nextInt(viewModel.getVinyls().size());
                        Vinyl vinyl = viewModel.getVinyls().get(index);
                        LendingState newState;

                        // Randomly choose a state
                        int stateIndex = random.nextInt(3); // 0 = AVAILABLE, 1 = RESERVED, 2 = BORROWED
                        switch (stateIndex) {
                            case 0:
                                newState = new AvailableState(vinyl);
                                System.out.println("Available");
                                break;
                            case 1:
                                newState = new ReservedState(vinyl);
                                System.out.println("Reserved");
                                int userIndex = random.nextInt(3);
                                switch (userIndex) {
                                    case 0 -> vinyl.setLastUser(user2);
                                    case 1 -> vinyl.setLastUser(user3);
                                    case 2 -> vinyl.setLastUser(user4);
                                }
                                break;
                            case 2:
                                newState = new BorrowedState(vinyl);
                                System.out.println("Borrowed");
                                userIndex = random.nextInt(3);
                                switch (userIndex) {
                                    case 0 -> vinyl.setLastUser(user2);
                                    case 1 -> vinyl.setLastUser(user3);
                                    case 2 -> vinyl.setLastUser(user4);
                                }
                                break;
                            default:
                                newState = new AvailableState(vinyl); // Default to Available in case something happens
                                break;
                        }

                        vinyl.setLendingState(newState);
                    });
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        simulationThread.start();
    }

    @FXML
    public void borrowAction() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        if (selectedVinyl != null) {
            if (selectedVinyl.getLendingState() instanceof AvailableState) {
                System.out.println("Cannot borrow. Vinyl is not reserved.");
            } else if (selectedVinyl.getLendingState() instanceof ReservedState) {
                viewModel.borrowVinyl(selectedVinyl, currentUser);
                selectedVinyl.setLastUser(currentUser);
                selectedVinyl.setLendingState(new BorrowedState(selectedVinyl));
            } else {
                System.out.println("Cannot borrow. Vinyl is already borrowed.");
            }
        } else {
            System.out.println("Please select a vinyl to borrow.");
        }
    }

    @FXML
    public void returnAction() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        if (selectedVinyl != null) {
            if (selectedVinyl.getLendingState() instanceof BorrowedState) {
                viewModel.returnVinyl(selectedVinyl, currentUser);
                selectedVinyl.setLastUser(null);
                selectedVinyl.setLendingState(new AvailableState(selectedVinyl));
            } else {
                System.out.println("Cannot return. Vinyl is not borrowed.");
            }
        } else {
            System.out.println("Please select a vinyl to return.");
        }
    }

    @FXML
    public void reserveAction() {
        Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
        if (selectedVinyl != null) {
            if (selectedVinyl.getLendingState() instanceof AvailableState) {
                viewModel.reserveVinyl(selectedVinyl, currentUser);
                selectedVinyl.setLastUser(currentUser);
                selectedVinyl.setLendingState(new ReservedState(selectedVinyl));
            } else {
                System.out.println("Cannot reserve. Vinyl is not available.");
            }
        } else {
            System.out.println("Please select a vinyl to reserve.");
        }
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
}