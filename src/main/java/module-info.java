module group.vinylproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens group6.vinylproject to javafx.fxml;
    exports group6.vinylproject;
    exports view;
    opens view to javafx.fxml;
}