package group6.vinylproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
<<<<<<< HEAD
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/group6/vinylproject/View/VinylView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
=======
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/group6/vinylproject/View/VinylView.fxml"));
        primaryStage.setTitle("Vinyl Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
>>>>>>> Rageevan
    }

    public static void main(String[] args) {
        launch(args);
    }
}
