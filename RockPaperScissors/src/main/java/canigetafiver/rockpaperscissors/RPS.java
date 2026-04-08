package canigetafiver.rockpaperscissors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RPS extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RPS.class.getResource("rps-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("Rock, Paper, Scissors GUI");
        stage.setScene(scene);
        stage.show();
    }
    public void switchScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RPS.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    }
}
