package canigetafiver.rockpaperscissors;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RPSController {
    @FXML
    private Button exitButton;
    @FXML
    private Button startGame;

    @FXML
    protected void onAboutButtonClick() {
        Alert gameInfo = new Alert(Alert.AlertType.INFORMATION);
        gameInfo.setTitle("RPS Game Info");
        gameInfo.setHeaderText("Game Information");
        gameInfo.setContentText("This is a JavaFX Rock-Paper-Scissors game where you play against a computer.\n"
                + "Choose to play against a random strategy or a machine learning strategy.\n"
                + "The game will keep track of your wins, losses, and ties.\n"
                + "\n"
                + "How to play:\n"
                + "1. Click the 'Start Game' button to begin.\n"
                + "2. Choose the number of rounds you want to play.\n"
                + "3. In each round, select Rock, Paper, or Scissors.\n"
                + "4. The computer will make its move, and the winner will be displayed.\n"
                + "5. The game will keep track of your statistics.\n"
                + "\n"
                + "Have fun!");
        gameInfo.showAndWait();
    }
    @FXML
    protected void onExitButtonClick(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onStartGameButtonClick() {
        SceneSwitch sw = new SceneSwitch((Stage) startGame.getScene().getWindow());
        sw.switchScene("rpsRounds.fxml");
    }

}
