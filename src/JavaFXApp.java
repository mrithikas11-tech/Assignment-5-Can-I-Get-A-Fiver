import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class JavaFXApp extends Application {

    private int currentRound;
    private int totalRounds;
    private int humanWins;
    private int compWins;
    private int ties;
    private Computer computer;
    private Player humanPlayer;
    private RoundSequence roundSequence;
    private MLStrategy mlStrategy;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Color.LIGHTGRAY);
        stage.setTitle("Rock-Paper-Scissors Game Menu");
        stage.setScene(scene);
        stage.setHeight(450);
        stage.setWidth(500);
        stage.setResizable(false);
        stage.setX(500);
        stage.setY(200);

        MenuBar menuBar=createMenuBar(stage, scene); // future method
        root.setTop(menuBar);

        Text text = new Text();
        text.setText("Welcome to the Rock-Paper-Scissors Game!");
        text.setFont(Font.font("Arial", 15));
        GridPane grid = new GridPane();
        grid.setVgap(20);

        Button startButton = new Button("Start Game");
        Button aboutButton = new Button("About");
        Button exitButton = new Button("Exit");

        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHalignment(startButton, javafx.geometry.HPos.CENTER);
        grid.setHalignment(aboutButton, javafx.geometry.HPos.CENTER);
        grid.setHalignment(exitButton, javafx.geometry.HPos.CENTER);

        grid.add(aboutButton, 0, 0);
        grid.add(startButton, 0, 1);
        grid.add(exitButton, 0, 2);

        VBox centerBox = new VBox(20, text, grid);
        centerBox.setAlignment(javafx.geometry.Pos.CENTER);
        root.setCenter(centerBox);

        // start button action
        startButton.setOnAction(e -> {
            showGameSetup(stage, scene);
        });

        // about button action
        aboutButton.setOnAction(e -> {
            showAbout(stage, scene);
        });

        // exit button action
        exitButton.setOnAction(e -> {
            Platform.exit();
        });
        stage.show();
    }

    /**
     * creates MenuBar with Game and Help menus
     * 
     * @param stage
     * @param mainScene
     * @return
     */
    private MenuBar createMenuBar(Stage stage,Scene mainScene) {
        MenuBar menuBar= new MenuBar();
        Menu gameMenu= new Menu("Game");
        MenuItem newGameItem= new MenuItem("New Game");
        MenuItem exitItem= new MenuItem("Exit");
        newGameItem.setOnAction(e -> showGameSetup(stage,mainScene));
        exitItem.setOnAction(e -> Platform.exit());
        gameMenu.getItems().addAll(newGameItem,exitItem);

        Menu helpMenu= new Menu("Help");
        MenuItem aboutItem= new MenuItem("About");
        aboutItem.setOnAction(e ->showAbout(stage,mainScene));
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(gameMenu,helpMenu);
        return menuBar;
    }

    /**
     * Shows game setup screen to select # of rounds
     * 
     * @param stage
     * @param mainScene
     */
    private void showGameSetup(Stage stage, Scene mainScene) {
        BorderPane setupRoot=new BorderPane();
        setupRoot.setTop(createMenuBar(stage,mainScene));
        Label roundLabel=new Label("Select number of rounds.\nDefault is 20.");
        roundLabel.setFont(Font.font("Arial",14));
        VBox setupBox= new VBox(15);
        setupBox.setAlignment(javafx.geometry.Pos.CENTER);
        Spinner<Integer> roundSpinner = new Spinner<>();
        roundSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 20));

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(e->{
            totalRounds =roundSpinner.getValue();
            stage.setTitle("Rock-Paper-Scissors Game");
            startNewGame(stage,mainScene);
        });

        setupBox.getChildren().addAll(roundLabel,roundSpinner,startGameButton);
        setupRoot.setCenter(setupBox);
        Scene setupScene = new Scene(setupRoot, 500, 450);
        stage.setTitle("Game Setup");
        stage.setScene(setupScene);
    }

    private void startNewGame(Stage stage,Scene mainScene) {
        if (mlStrategy!=null) {
            mlStrategy.saveToFile("frequency_data.txt");
        }

        currentRound=1;
        humanWins=0;
        compWins=0;
        ties=0;

        //Backend objects (resued code from Assign. #4)
        humanPlayer=new Player() {};
        humanPlayer.setPlayerName("Human");
        roundSequence= new RoundSequence();
        mlStrategy=new MLStrategy(roundSequence, 5);
        computer=new Computer(mlStrategy);
        roundSequence.updatePlayers(humanPlayer,computer);

        //gameplay scene
        BorderPane gameRoot=new BorderPane();
        gameRoot.setTop(createMenuBar(stage,mainScene));
        VBox roundsBox =new VBox(10);
        roundsBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        roundsBox.setStyle("-fx-padding: 10;");

        Label roundLabel =new Label("Round " + currentRound + " of " + totalRounds);
        roundLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        //Human section
        VBox humanBox =new VBox (5);
        Label humanLabel= new Label("Human");
        humanLabel.setFont (Font.font("Arial", FontWeight.BOLD, 16));
        Label choicesLabel =new Label("Choose:");
        choicesLabel.setFont(Font.font("Arial", 14));
        HBox choicesBox= new HBox(10);
        Button rockButton =new Button("Rock");
        Button paperButton=new Button("Paper");
        Button scissorsButton= new Button("Scissors");
        choicesBox.getChildren().addAll(choicesLabel,rockButton,paperButton, scissorsButton);
        choicesBox.setAlignment(javafx.geometry.Pos.CENTER);
        Label humanChoiceLabel =new Label("Human chooses: ");
        humanBox.getChildren().addAll(humanLabel, choicesBox, humanChoiceLabel);
        humanBox.setAlignment(javafx.geometry.Pos.CENTER);

        //Computer section
        VBox compBox = new VBox(5);
        Label compLabel = new Label("Computer");
        compLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Label predictedChoiceLabel = new Label("Predicted human choice: ");
        Label compChoiceLabel = new Label("Computer chooses: ");
        compBox.getChildren().addAll(compLabel, predictedChoiceLabel, compChoiceLabel);
        compBox.setAlignment(javafx.geometry.Pos.CENTER);

        //results section
        //winner
        VBox winnerBox=new VBox(5);
        Label winnerLabel=new Label("The winner is: ");
        winnerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        winnerBox.getChildren().add(winnerLabel);
        winnerBox.setAlignment(javafx.geometry.Pos.CENTER);
        //stats
        VBox statisticsBox=new VBox(3);
        Label statisticsLabel= new Label("Statistics");
        statisticsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Label humanWinsLabel= new Label("Human wins: 0");
        Label compWinsLabel =new Label("Computer wins: 0");
        Label tiesLabel= new Label("Ties: 0");
        statisticsBox.getChildren().addAll(statisticsLabel,humanWinsLabel,compWinsLabel,tiesLabel);
        statisticsBox.setAlignment(javafx.geometry.Pos.CENTER);

        Label statusLabel = new Label("Welcome to the Rock-Paper-Scissors game!");
        statusLabel.setFont(Font.font("Arial", 12));
        Button returnButton = new Button("Return to Main Menu");
        returnButton.setOnAction(ev -> {
            //saves frequency data when leaving game
            mlStrategy.saveToFile("frequency_data.txt");
            stage.setTitle("Rock-Paper-Scissors");
            stage.setScene(mainScene);
        });

        //connect RPS buttons to game logic
        rockButton.setOnAction(e -> {
            playRound(Sign.ROCK, humanChoiceLabel,predictedChoiceLabel,
                    compChoiceLabel,winnerLabel,roundLabel,
                    humanWinsLabel,compWinsLabel,tiesLabel,statusLabel,
                    rockButton,paperButton,scissorsButton);
        });

        paperButton.setOnAction(e -> {
            playRound(Sign.PAPER, humanChoiceLabel,predictedChoiceLabel,
                    compChoiceLabel,winnerLabel,roundLabel,
                    humanWinsLabel,compWinsLabel,tiesLabel,statusLabel,
                    rockButton,paperButton,scissorsButton);
        });

        scissorsButton.setOnAction(e -> {
            playRound(Sign.SCISSORS, humanChoiceLabel,predictedChoiceLabel,
                    compChoiceLabel,winnerLabel,roundLabel,
                    humanWinsLabel,compWinsLabel,tiesLabel,statusLabel,
                    rockButton,paperButton,scissorsButton);
        });

        roundsBox.getChildren().addAll(roundLabel,humanBox,compBox,winnerBox,statisticsBox,returnButton,
                statusLabel);
        gameRoot.setCenter(roundsBox);
        Scene gameScene = new Scene(gameRoot, 500, 450);
        stage.setScene(gameScene);
    }

    /**
     * Plays single round of RPS, called when player clicks a Sign button
     * 
     */
    private void playRound(Sign humanChoice,
            Label humanChoiceLabel,Label predictedLabel,
            Label compChoiceLabel,Label winnerLabel,
            Label roundLabel,
            Label humanWinsLabel,Label compWinsLabel,Label tiesLabel,Label statusLabel,
            Button rockButton,Button paperButton,Button scissorsButton) {
        humanPlayer.setCurSign(humanChoice); // choices
        Sign compChoice = computer.makeChoice();
        Sign prediction = mlStrategy.getLastPrediction();
        roundSequence.updateRoundSequence();

        // update winner
        String winner = determineWinner(humanChoice,compChoice);
        if (winner.equals("Human")) {
            humanWins++;
        } else if (winner.equals("Computer")) {
            compWins++;
        } else {
            ties++;
        }

        // update labels
        humanChoiceLabel.setText("Human chooses: "+ displaySign(humanChoice));
        if (prediction != null) {
            predictedLabel.setText("Predicted human choice: "+ displaySign(prediction));
        } else {
            predictedLabel.setText("Predicted human choice: Random");
        }
        compChoiceLabel.setText("Computer chooses: "+ displaySign(compChoice));
        winnerLabel.setText("The winner: "+ winner);
        humanWinsLabel.setText("Human wins: " +humanWins);
        compWinsLabel.setText("Computer wins: " +compWins);
        tiesLabel.setText("Ties: "+ties);

        // gamoever check
        if (currentRound >= totalRounds) {
            rockButton.setDisable(true);
            paperButton.setDisable(true);
            scissorsButton.setDisable(true);

            // display final result
            String finalResult;
            if (humanWins>compWins) {
                finalResult="Game over! Human wins!";
            } else if (compWins>humanWins) {
                finalResult="Game over! Computer wins!";
            } else {
                finalResult="Game over! It's a tie!";
            }
            statusLabel.setText(finalResult);
            roundLabel.setText("Round: "+currentRound+" (End)");

            mlStrategy.saveToFile("frequency_data.txt");
        } else {
            currentRound++;
            roundLabel.setText("Round: "+ currentRound);
            statusLabel.setText("Round: "+(currentRound - 1)+" finished. Choose your move.");
        }
    }

    private String determineWinner(Sign humanChoice, Sign compChoice) {
        if (humanChoice==compChoice) {
            return "Tie";
        }
        if ((humanChoice==Sign.ROCK &&compChoice==Sign.SCISSORS) ||
                (humanChoice==Sign.PAPER &&compChoice==Sign.ROCK) ||
                (humanChoice==Sign.SCISSORS&&compChoice==Sign.PAPER)) {
            return "Human";
        }
        return "Computer";
    }

    /**
     * Returns display name for Sign enuum
     */
    private String displaySign(Sign sign) {
        if (sign ==Sign.ROCK) {
            return "Rock";
        } else if (sign== Sign.PAPER) {
            return "Paper";
        } else if (sign== Sign.SCISSORS) {
            return "Scissors";
        }
        return "Unknown";
    }

    /**
     * Shows about page
     * 
     * @param stage
     * @param mainScene
     */
    private void showAbout(Stage stage,Scene mainScene) {
        BorderPane aboutRoot = new BorderPane();
        aboutRoot.setTop(createMenuBar(stage,mainScene));
        VBox aboutBox = new VBox(15);
        aboutBox.setAlignment(javafx.geometry.Pos.CENTER);
        aboutBox.setStyle("-fx-padding: 20;");
        Label titleLabel = new Label("Rock-Paper-Scissors");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label infoLabel = new Label(
                "This is a JavaFX Rock-Paper-Scissors game where you play against a computer.\n"
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
        infoLabel.setFont(Font.font("Arial", 13));

        Label authorLabel =new Label("By: CanIGetAFiver");
        authorLabel.setFont(Font.font("Arial", 12));

        Button backButton= new Button("Back to Main Menu");
        backButton.setOnAction(ev -> {
            stage.setScene(mainScene);
        });
        aboutBox.getChildren().addAll(titleLabel,infoLabel,authorLabel,backButton);
        aboutRoot.setCenter(aboutBox);
        Scene aboutScene=new Scene(aboutRoot,500,450);
        stage.setTitle("About");
        stage.setScene(aboutScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
