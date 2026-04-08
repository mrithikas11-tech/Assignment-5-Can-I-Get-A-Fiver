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

        MenuBar menuBar = createMenuBar(stage, scene); // future method
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
        // aboutButton.setLayoutX(150);
        // aboutButton.setLayoutY(100);

        // startButton.setLayoutX(150);
        // startButton.setLayoutY(150);

        // exitButton.setLayoutX(150);
        // exitButton.setLayoutY(200);
        grid.add(aboutButton, 0, 0);
        grid.add(startButton, 0, 1);
        grid.add(exitButton, 0, 2);

        VBox centerBox = new VBox(20, text, grid);
        centerBox.setAlignment(javafx.geometry.Pos.CENTER);
        root.setCenter(centerBox);

        // start button action

        startButton.setOnAction(e -> {
            Label gameLabel = new Label(
                    "Game Started! \n Please select the number of rounds you want to play. \n The default is 20 rounds.");
            VBox gameBox = new VBox(gameLabel);
            Scene gameScene = new Scene(gameBox, 400, 400);
            gameBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
            Spinner roundsSpinner = new Spinner();
            roundsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 20));
            gameBox.getChildren().add(roundsSpinner);
            Button startRoundsButton = new Button("Start Rounds");
            gameBox.getChildren().add(startRoundsButton);
            startRoundsButton.setOnAction(ev -> {

                // main rounds scene

                VBox roundsBox = new VBox(new Label("Rounds Started!"));
                Scene roundsScene = new Scene(roundsBox, 300, 200);
                roundsBox.setSpacing(20);
                stage.setScene(roundsScene);
                int rounds = (int) roundsSpinner.getValue();
                System.out.println("Starting " + rounds + " rounds of Rock-Paper-Scissors!");
                Label roundsLabel = new Label("Round: " + 1); // Update this label in each round to show the current
                                                              // round number
                roundsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                roundsBox.getChildren().add(roundsLabel);

                // humanbox

                VBox humanBox = new VBox();
                Label humanLabel = new Label("Human:");
                humanLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                Label choicesLabel = new Label("Choose:");
                choicesLabel.setFont(Font.font("Arial", 14));
                HBox choicesBox = new HBox();
                Button rockButton = new Button("Rock");
                Button paperButton = new Button("Paper");
                Button scissorsButton = new Button("Scissors");
                choicesBox.getChildren().addAll(choicesLabel, rockButton, paperButton, scissorsButton);
                choicesBox.setAlignment(javafx.geometry.Pos.CENTER);
                Label humanChoiceLabel = new Label("Choice: " + "None");
                rockButton.setOnAction(er -> {
                    humanChoiceLabel.setText("Choice: Rock");
                });
                paperButton.setOnAction(ep -> {
                    humanChoiceLabel.setText("Choice: Paper");
                });
                scissorsButton.setOnAction(es -> {
                    humanChoiceLabel.setText("Choice: Scissors");
                });
                humanBox.getChildren().add(humanLabel);
                humanBox.getChildren().add(choicesBox);
                humanBox.getChildren().add(humanChoiceLabel);
                humanBox.setAlignment(javafx.geometry.Pos.CENTER);

                // choice button actions + logic

                // computerbox

                VBox computerBox = new VBox();
                Label computerLabel = new Label("Computer:");
                computerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                Label predictedHumanChoiceLabel = new Label("Predicted Human Choice: " + "None");
                Label computerChoiceLabel = new Label("Therefore Computer Choice: " + "None");
                computerBox.getChildren().add(computerLabel);
                computerBox.getChildren().add(predictedHumanChoiceLabel);
                computerBox.getChildren().add(computerChoiceLabel);
                computerBox.setAlignment(javafx.geometry.Pos.CENTER);

                // Winnerbox

                VBox winnerBox = new VBox();
                Label winnerLabel = new Label("Winner:" + " None" + "!");
                winnerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                winnerBox.getChildren().add(winnerLabel);
                winnerBox.setAlignment(javafx.geometry.Pos.CENTER);

                // Statisticsbox

                VBox statisticsBox = new VBox();
                Label statisticsLabel = new Label("Statistics:");
                statisticsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                Label humanWinsLabel = new Label("Human Wins: " + 0);
                Label computerWinsLabel = new Label("Computer Wins: " + 0);
                Label tiesLabel = new Label("Ties: " + 0);
                statisticsBox.getChildren().add(statisticsLabel);
                statisticsBox.getChildren().add(humanWinsLabel);
                statisticsBox.getChildren().add(computerWinsLabel);
                statisticsBox.getChildren().add(tiesLabel);
                statisticsBox.setAlignment(javafx.geometry.Pos.CENTER);

                // return to main menu button

                Button returnButton = new Button("Return to Main Menu");
                returnButton.setOnAction(ev2 -> {
                    stage.setScene(scene);
                });

                // add everything to roundsbox

                roundsBox.getChildren().add(humanBox);
                roundsBox.getChildren().add(computerBox);
                roundsBox.getChildren().add(winnerBox);
                roundsBox.getChildren().add(statisticsBox);
                roundsBox.getChildren().add(returnButton);
                roundsBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            });

            stage.setTitle("Game");
            stage.setScene(gameScene);
            stage.show();
        });

        // add actual about information here
        // about button action
        aboutButton.setOnAction(e -> {
            showAbout(stage, scene);
        });

        // exit button action

        exitButton.setOnAction(e -> {
            Scene exitScene = new Scene(new VBox(new Label("Thank you for playing!")), 300, 200);
            stage.setTitle("Exit");
            stage.setScene(exitScene);
            stage.close();
        });

        stage.show();

    private void showAbout(Stage stage, Scene mainScene) {
        BorderPane aboutRoot = new BorderPane();
        aboutRoot.setTop(createMenuBar(stage, mainScene));
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

        Label authorLabel = new Label("By: CanIGetAFiver");
        authorLabel.setFont(Font.font("Arial", 12));

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(ev -> {
            stage.setScene(mainScene);
        });
        aboutBox.getChildren().addAll(titleLabel, infoLabel, authorLabel, backButton);
        aboutRoot.setCenter(aboutBox);
        Scene aboutScene = new Scene(aboutRoot, 500, 450);
        stage.setTitle("About");
        stage.setScene(aboutScene);
    }
    
}
}

public static void main(String[] args) {
    launch(args);
}
