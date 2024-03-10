package model;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import model.states.*;
import java.util.Random;

public class Simulation {


    private static final User user2 = new User("User 2");
    private static final User user3 = new User("User 3");
    private static final User user4 = new User("User 4");

    public static void simulateActions(ObservableList<Vinyl> vinyls) {
        Thread simulationThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(2000); // 2 second actions
                    Platform.runLater(() -> {
                        int index = random.nextInt(vinyls.size());
                        Vinyl vinyl = vinyls.get(index);
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
                                assignUserToReservation(vinyl);
                                break;
                            case 2:
                                newState = new BorrowedState(vinyl);
                                System.out.println("Borrowed");
                                assignUserToBorrowing(vinyl);
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

    private static void assignUserToReservation(Vinyl vinyl) {
        Random random = new Random();
        int userIndex = random.nextInt(3);
        switch (userIndex) {
            case 0:
                vinyl.setLastUser(user2);
                break;
            case 1:
                vinyl.setLastUser(user3);
                break;
            case 2:
                vinyl.setLastUser(user4);
                break;
        }
    }

    private static void assignUserToBorrowing(Vinyl vinyl) {
        Random random = new Random();
        int userIndex = random.nextInt(3);
        switch (userIndex) {
            case 0:
                vinyl.setLastUser(user2);
                break;
            case 1:
                vinyl.setLastUser(user3);
                break;
            case 2:
                vinyl.setLastUser(user4);
                break;
        }
    }
}
