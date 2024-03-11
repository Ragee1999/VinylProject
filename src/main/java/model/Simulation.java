package model;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import model.states.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class Simulation {
        private static final User user2 = new User("User 2");
        private static final User user3 = new User("User 3");
        private static final User user4 = new User("User 4");
        private static final Map<Vinyl, Lock> vinylLocks = new HashMap<>();
        private static ExecutorService executor = Executors.newFixedThreadPool(6);

        public static void simulateActions(ObservableList<Vinyl> vinyls) {
            Thread simulationThread = new Thread(() -> {
                Random random = new Random();
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Platform.runLater(() -> {
                            for (Vinyl vinyl : vinyls) {
                                int delay = random.nextInt(2000) + 1000; //delay between 1 to 3 seconds
                                executor.submit(() -> {
                                    try {
                                        Thread.sleep(delay);
                                        performActionOnVinyl(vinyl, random);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                });
                            }
                        });
                        Thread.sleep(5000); // Main delay for 5sec
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            simulationThread.start();
        }

        private static void performActionOnVinyl(Vinyl vinyl, Random random) {
            Lock lock = getLockForVinyl(vinyl);
            lock.lock();
            try {
                LendingState currentState = vinyl.getLendingState();
                if (currentState instanceof AvailableState) {
                    int action = random.nextInt(2); // 0 = reserve, 1 = borrow (threads either reserve or borrow it)
                    if (action == 0) {
                        vinyl.reserve();
                        assignUserToReservation(vinyl);
                        System.out.println(vinyl.getTitle() + " is now reserved by " + vinyl.getLastUserName());
                    } else if (action == 1) {
                        vinyl.borrow();
                        assignUserToBorrowing(vinyl);
                        System.out.println(vinyl.getTitle() + " is now borrowed by " + vinyl.getLastUserName());
                    }
                } else if (currentState instanceof ReservedState) {
                    vinyl.borrow();
                    System.out.println(vinyl.getTitle() + " is now borrowed by " + vinyl.getLastUserName());
                } else if (currentState instanceof BorrowedState) {
                    vinyl.returnVinyl();
                    vinyl.setLastUser(null);
                    System.out.println(vinyl.getTitle() + " is returned and now available.");
                }
            } finally {
                lock.unlock();
            }
        }

        private static Lock getLockForVinyl(Vinyl vinyl) {
            return vinylLocks.computeIfAbsent(vinyl, k -> new ReentrantLock());
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

