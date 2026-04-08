import java.util.Scanner;

public class GameRPSHuVsCo extends Game {

    private Player human;
    private Player computer;
    private final static int ROUNDS = 20;
    private final Scanner scnr;
    private final Strategy strategy;
    private final RoundSequence roundSequence;

    public GameRPSHuVsCo(Scanner scnr, Strategy strategy, RoundSequence roundSequence) {
        this.scnr = scnr;
        this.strategy = strategy;
        this.roundSequence = roundSequence;
    }

    @Override
    public void runGame() {
        human    = new HumanPlayer(scnr);
        computer = new Computer(strategy);

        if (roundSequence != null) {
            roundSequence.updatePlayers(human, computer);
        }

        GameRule rules = new GameRule(human, computer);

        for (int count = 1; count <= ROUNDS; count++) {
            System.out.println("Round " + count);
            rules.getRPS();
            if (roundSequence != null) {
                roundSequence.updateRoundSequence();
            }
        }

        System.out.println("\n---GAME OVER---");
        rules.printResult();

        if (rules.getHumanWins() > rules.getComputerWins()) {
            System.out.println(human.getPlayerName() + " wins the game!");
        } else if (rules.getComputerWins() > rules.getHumanWins()) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("It's a draw!");
        }
        System.out.println("---------------\n");
    }
}