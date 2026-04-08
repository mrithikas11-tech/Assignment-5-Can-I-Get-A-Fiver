package canigetafiver.rockpaperscissors;

import java.util.Scanner;

public class GameMenu {

    public void startMenu() {
        Scanner scnr = new Scanner(System.in);
        boolean runGame = true;

        System.out.println("Welcome to the game!");

        while (runGame) {
            try {
                System.out.println("Enter -r for Random strategy, -m for Smart strategy, or 0 to exit:");
                String input = scnr.next();

                if (input.equals("0")) {
                    runGame = false;
                } else if (input.equalsIgnoreCase("-r")) {
                    Strategy strategy = new RandomStrategy();
                    Game startGame = new GameRPSHuVsCo(scnr, strategy, null);
                    startGame.runGame();
                } else if (input.equalsIgnoreCase("-m")) {
                    RoundSequence sharedSeq = new RoundSequence();
                    Strategy strategy = new MLStrategy(sharedSeq, 5);
                    Game startGame = new GameRPSHuVsCo(scnr, strategy, sharedSeq);
                    startGame.runGame();
                } else {
                    System.out.println("Not valid input, try again");
                }
            } catch (Exception e) {
                System.out.println("Not valid input, try again");
                scnr.nextLine();
            }
        }

        scnr.close();
        System.out.println("Thank you for playing");
    }

    public static void main(String[] args) {
        GameMenu menu = new GameMenu();
        menu.startMenu();
    }
}