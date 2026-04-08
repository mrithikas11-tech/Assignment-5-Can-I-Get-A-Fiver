
/**
 * Find winner and keep and print score
 * would like this class to just find the winner/ rules of rock paper scissors 
 * future implementation another class would keep score.
 */
public class GameRule {
    private int humanWins =0;
    private int computerWins=0;
    private int draws=0;
    Player human;
    Player computer;

    public GameRule(Player human, Player computer){
        this.human = human;
        this.computer = computer;
    }

    //rock == 0 paper = 1 scissor=2
    // handles wins for a single round of the game and prints out message for who won
    public void getRPS(){
        Sign humanChose = human.makeChoice();
        Sign computerChose = computer.makeChoice();
        if(humanChose == Sign.ROCK && computerChose== Sign.PAPER){
            System.out.println("You chose Rock. The computer chose Paper. Computer wins!");//Subject to change for name
            computerWins+=1;

        }
        else if(humanChose== Sign.ROCK && computerChose == Sign.SCISSORS){
            System.out.println("You chose Rock. The computer chose Scissors. Human wins!");
            humanWins+=1;
        }
        else if (humanChose == Sign.PAPER && computerChose == Sign.SCISSORS) {
            System.out.println("You chose Paper. The computer chose Scissors. Computer wins!");
            computerWins+=1;
        }
        else if (humanChose == Sign.PAPER && computerChose == Sign.ROCK) {
            System.out.println("You chose Paper. The computer chose Rock. Human wins!");
            humanWins+=1;
        }
        else if(humanChose== Sign.SCISSORS && computerChose == Sign.ROCK){
            System.out.println("You chose SCISSORS. The computer chose Rock. Computer wins!");
            computerWins+=1;
        }
        else if(humanChose== Sign.SCISSORS && computerChose== Sign.PAPER){
            System.out.println("You chose Scissors. The computer chose Paper. Human wins!");
            humanWins+=1;
        }
        else{
    
            switch (humanChose) {
                case Sign.ROCK -> {
                    System.out.println("You chose Rock. The computer chose Rock. Draw!");
                    draws+=1;
                }
                case Sign.SCISSORS -> {
                    System.out.println("You chose Scissors. The computer chose Scissors. Draw!");
                    draws+=1;
                }
                default -> {
                    System.out.println("You chose Paper. The computer chose Paper. Draw!");
                    draws+=1;
                }
            }
        }
        
        printResult();

    }

    public int getHumanWins(){
        return humanWins;
    }
    public int getComputerWins(){
        return computerWins;
    }
    public int getDraws(){
        return draws;
    }

    public void printResult(){
        System.out.println("Score: Human:" + humanWins + " Computer:"+ computerWins+ " Draws:"+ draws);
    }

}




