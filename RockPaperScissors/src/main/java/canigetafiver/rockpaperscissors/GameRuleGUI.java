package canigetafiver.rockpaperscissors;

public class GameRuleGUI extends GameRule{
    private int humanWins =0;
    private int computerWins=0;
    private int draws=0;
    Player human;
    Player computer;
    private String curWinner;
    private static final String HUMAN = "Human";
    private static final String COMPUTER = "Computer";
    private static final String DRAW = "Draw";

    public GameRuleGUI(Player human, Player computer) {
        super(human, computer);

    }


    //rock == 0 paper = 1 scissor=2
    // handles wins for a single round of the game and prints out message for who won
    @Override
    public void getRPS(){
        Sign humanChose = human.makeChoice();
        Sign computerChose = computer.makeChoice();
        if(humanChose == Sign.ROCK && computerChose== Sign.PAPER){
            computerWins+=1;
        }
        else if(humanChose== Sign.ROCK && computerChose == Sign.SCISSORS){
            humanWins+=1;
        }
        else if (humanChose == Sign.PAPER && computerChose == Sign.SCISSORS) {
            computerWins+=1;
        }
        else if (humanChose == Sign.PAPER && computerChose == Sign.ROCK) {
            humanWins+=1;
        }
        else if(humanChose== Sign.SCISSORS && computerChose == Sign.ROCK){
            computerWins+=1;
        }
        else if(humanChose== Sign.SCISSORS && computerChose== Sign.PAPER){
            humanWins+=1;
        }
        else{
            switch (humanChose) {
                case Sign.ROCK -> {
                    draws+=1;
                    curWinner= DRAW;
                }
                case Sign.SCISSORS -> {
                    draws+=1;
                    curWinner= DRAW;
                }
                default -> {
                    draws+=1;
                    curWinner= DRAW;
                }
            }
        }

    }

    public void getRPS(Sign theSign){
        super.human.setCurSign(theSign);
        Sign humanChose = super.human.getCurSign();
        Sign computerChose = super.computer.makeChoice();
        if(humanChose == Sign.ROCK && computerChose== Sign.PAPER){
            computerWins+=1;
            curWinner = COMPUTER;
        }
        else if(humanChose== Sign.ROCK && computerChose == Sign.SCISSORS){
            humanWins+=1;
            curWinner = HUMAN;
        }
        else if (humanChose == Sign.PAPER && computerChose == Sign.SCISSORS) {
            computerWins+=1;
            curWinner = COMPUTER;
        }
        else if (humanChose == Sign.PAPER && computerChose == Sign.ROCK) {
            humanWins+=1;
            curWinner = HUMAN;
        }
        else if(humanChose== Sign.SCISSORS && computerChose == Sign.ROCK){
            computerWins+=1;
            curWinner = COMPUTER;
        }
        else if(humanChose== Sign.SCISSORS && computerChose== Sign.PAPER){
            humanWins+=1;
            curWinner = HUMAN;
        }
        else{
            switch (humanChose) {
                case Sign.ROCK -> {
                    draws+=1;
                    curWinner= DRAW;
                }
                case Sign.SCISSORS -> {
                    draws+=1;
                    curWinner= DRAW;
                }
                default -> {
                    draws+=1;
                    curWinner= DRAW;
                }
            }
        }
    }
    @Override
    public int getHumanWins(){
        return humanWins;
    }
    @Override
    public int getComputerWins(){
        return computerWins;
    }
    @Override
    public int getDraws(){
        return draws;
    }
    public String getCurWinner(){
        return curWinner;
    }

}
