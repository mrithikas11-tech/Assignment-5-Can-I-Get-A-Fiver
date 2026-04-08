package canigetafiver.rockpaperscissors;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RPSController {


    @FXML
    private Label roundLabel;
    @FXML
    private Alert gameInfo;

    @FXML
    private Button exitButton;
    @FXML
    private Button startGame;


    @FXML
    protected void onInfoButtonClick() {
        gameInfo = new Alert(Alert.AlertType.INFORMATION);
        gameInfo.setTitle("RPS Game Info");
        gameInfo.setHeaderText("Game Information");
        gameInfo.setContentText("This is a GUI based game of Rock, Paper, " +
                        "Scissors against a simple Machine learning computer");
        gameInfo.showAndWait();
    }
    @FXML
    protected void onGameInfoButtonClick(){gameInfo.setTitle("RPS Info");}
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
