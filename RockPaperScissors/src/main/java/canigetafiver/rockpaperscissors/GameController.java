package canigetafiver.rockpaperscissors;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController {
    @FXML
    private Label roundsNum;
    @FXML
    private Spinner<Integer> intSpinner;
    @FXML
    private Button setRounds;
    @FXML
    private Button roundsOkay;
    @FXML
    private Button startButton;
    @FXML
    private Button doneButton;
    @FXML
    private Button rockButton;
    @FXML
    private Button paperButton;
    @FXML
    private Button scissorsButton;
    @FXML
    private Label t;
    private int rounds = 20; //default Rounds
    @FXML
    private GridPane gpGame;
    @FXML
    private GridPane gpStart;
    @FXML
    private Label curRound;
    @FXML
    private Label hWins;
    @FXML
    private Label cWins;
    @FXML
    private Label draws;
    @FXML
    private Label hChoice;
    @FXML
    private Label predictHChoice;
    @FXML
    private Label cChoice;
    @FXML
    private Label roundWinner;
    @FXML
    private Label changeRoundLabel;

    private GameRPSGUI theGame;
    private int counter = 1;
    private GameRuleGUI gRule;
    private Sign theSign;
    private Sign predictSign;
    private Player computer;

    @FXML
    protected void onSetRoundButtonClick() {
        intSpinner.setVisible(true);
        roundsOkay.setVisible(true);
        rounds = intSpinner.getValue();

    }
    @FXML
    protected void onSetRoundOkayButtonClick() {
        intSpinner.setVisible(false);
        roundsOkay.setVisible(false);
        rounds = intSpinner.getValue();
        Stage stage = (Stage) roundsOkay.getScene().getWindow();
        stage.setTitle("Rock Paper Scissors GUI: "+ rounds+ " Rounds");
        roundsNum.setText("Rounds: " +rounds);

    }
    @FXML
    protected void onStartButtonClick(){
        setRounds.setVisible(false);
        roundsNum.setVisible(false);
        startButton.setVisible(false);
        changeRoundLabel.setVisible(false);
        gpGame.setVisible(true);
        gpStart.setVisible(false);
        cChoice.setVisible(true);
        predictHChoice.setVisible(true);
        theGame = new GameRPSGUI(new RoundSequence(), rounds);
        gRule = theGame.getRules();
        computer = theGame.getComputer();
        counter = 1;
    }

    @FXML
    protected void onRockButtonClick() {
        hChoice.setText("Rock");
        theSign = Sign.ROCK;
        updateScreen();
    }

    @FXML
    protected void onPaperButtonClick() {
        hChoice.setText("Paper");
        theSign = Sign.PAPER;
        updateScreen();
    }

    @FXML
    protected void onScissorsButtonClick() {
        hChoice.setText("Scissors");
        theSign = Sign.SCISSORS;
        updateScreen();
    }
    @FXML
    protected void updateScreen() {
        if(counter<=rounds){
            theGame.runRound(theSign);
            curRound.setText(String.valueOf(counter));
            hWins.setText(String.valueOf(gRule.getHumanWins()));
            cWins.setText(String.valueOf(gRule.getComputerWins()));
            draws.setText(String.valueOf(gRule.getDraws()));
            cChoice.setText(computer.getCurSign().name());
            if(computer.getPredictedMove() != null){
                predictHChoice.setText(computer.getPredictedMove().name());
            }
            roundWinner.setText(gRule.getCurWinner());

            if(counter == rounds){
                rockButton.setDisable(true);
                paperButton.setDisable(true);
                scissorsButton.setDisable(true);
                gpStart.setVisible(true);
                doneButton.setVisible(true);
            }
        }

        counter+=1;
    }
    @FXML
    protected void onDoneButtonClick(){
        Stage stage = (Stage)doneButton.getScene().getWindow();
        SceneSwitch sw = new SceneSwitch(stage);
        sw.switchScene("rps-menu.fxml");
    }


}
