module canigetafiver.rockpaperscissors {
    requires javafx.controls;
    requires javafx.fxml;


    opens canigetafiver.rockpaperscissors to javafx.fxml;
    exports canigetafiver.rockpaperscissors;
}